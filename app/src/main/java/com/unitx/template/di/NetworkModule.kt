package com.unitx.template.di

import coil3.ImageLoader
import com.unitx.template.data.coil.CoilImageLoader
import com.unitx.template.data.network.DropboxClient
import com.unitx.template.data.network.DropboxCloudSourceImpl
import com.unitx.template.data.network.DropboxService
import com.unitx.template.data.network.OkHttpInterceptorClient
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {

    single<OkHttpClient>(named("retrofit")) {
        OkHttpInterceptorClient.getRetrofitInstance()
    }

    single<OkHttpClient>(named("image")) {
        OkHttpInterceptorClient.getImageClient()
    }

    single<DropboxService> {
        DropboxClient
            .getInstance(get(), get(named("retrofit")))
            .create(DropboxService::class.java)
    }

    single<ImageLoader> {
        CoilImageLoader.getInstance(get(), get(named("image")))
    }

    singleOf(::DropboxCloudSourceImpl)
}