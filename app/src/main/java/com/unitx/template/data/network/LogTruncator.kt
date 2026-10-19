package com.unitx.template.data.network

import android.util.Log
import com.unitx.template.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

/**
 * SAFE_TRUNCATED -> logs up to maxBodyLogBytes via response.peekBody(). Cannot OOM,
 *                   no matter how big the real response is. Use this day to day.
 *
 * FULL_UNSAFE    -> old HttpLoggingInterceptor.Level.BODY behavior — logs the
 *                   COMPLETE response body.
 *
 * NONE           -> no body logging at all.
 *
 * Just change the line below, rebuild — no need to touch RetrofitClient/2/3.
 */
object DebugLogging {
    var mode: LogMode = LogMode.SAFE_TRUNCATED
    var maxBodyLogBytes: Long = 10_000L
}

enum class LogMode { NONE, SAFE_TRUNCATED, FULL_UNSAFE }
private val fullBodyInterceptor: HttpLoggingInterceptor by lazy {
    HttpLoggingInterceptor { message -> Log.d("OkHttp", message) }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}


private val effectiveMode: LogMode
    get() = if (BuildConfig.DEBUG) DebugLogging.mode else LogMode.SAFE_TRUNCATED

fun bodyLoggingInterceptor(tag: String = "OkHttp"): Interceptor {
    return Interceptor { chain ->
        when (effectiveMode) {
            LogMode.NONE -> chain.proceed(chain.request())
            LogMode.SAFE_TRUNCATED -> {
                val response = chain.proceed(chain.request())
                logTruncated(response, tag)
                response
            }
            LogMode.FULL_UNSAFE -> fullBodyInterceptor.intercept(chain)
        }
    }
}

private fun logTruncated(response: Response, tag: String) {
    try {
        val cap = DebugLogging.maxBodyLogBytes
        val peeked = response.peekBody(cap)
        val body = peeked.string()
        val suffix = if (body.length >= cap) "... [TRUNCATED, raise DebugLogging.maxBodyLogBytes to see more]" else ""
        Timber.tag(tag).d("Response [${response.code}] ${response.request.url}\n$body$suffix")
    } catch (e: Exception) {
        Timber.tag(tag).w("Could not log response body: ${e.message}")
    }
}