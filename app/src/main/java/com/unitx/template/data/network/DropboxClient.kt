package com.unitx.template.data.network

import com.unitx.template.data.constant.NetworkConstant
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object DropboxClient {

    @Volatile
    private var INSTANCE: Retrofit? = null
    fun getInstance(moshiClientInstance: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return INSTANCE ?: synchronized(this) {
            val instance = Retrofit.Builder()
                .baseUrl(NetworkConstant.DROPBOX.baseUrl)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshiClientInstance))
                .build()
            INSTANCE = instance
            instance
        }
    }
}