package com.unitx.template.data.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import timber.log.Timber
import java.util.concurrent.TimeUnit

object OkHttpInterceptorClient {

    private val headInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("Accept", "application/json")
            .build()
        Timber.tag("GtracClient")
            .d("Request: ${request.method} ${request.url}")
        chain.proceed(request)
    }

    fun getRetrofitInstance(): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(headInterceptor)
            .addInterceptor(bodyLoggingInterceptor(tag = "OkHttp"))
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    fun getImageClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }
}