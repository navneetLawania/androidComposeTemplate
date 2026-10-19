package com.unitx.template.data.coil

import android.content.Context
import coil3.ImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import coil3.request.crossfade
import okhttp3.OkHttpClient

object CoilImageLoader {

    fun getInstance(context: Context, okHttpClient: OkHttpClient): ImageLoader {
        return ImageLoader.Builder(context)
            .memoryCache {
                MemoryCache.Builder()
                    .maxSizePercent(context, 0.25)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(context.cacheDir.resolve("image_cache"))
                    .maxSizeBytes(10L * 1024 * 1024)
                    .build()
            }
            .components {
                add(OkHttpNetworkFetcherFactory(callFactory = { okHttpClient }))
            }
            .crossfade(true)
            .build()
    }
}