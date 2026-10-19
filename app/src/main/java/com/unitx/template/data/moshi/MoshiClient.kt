package com.unitx.template.data.moshi

import com.squareup.moshi.Moshi

object MoshiClient {
    fun getInstance(): Moshi {
        return Moshi.Builder().build()
    }
}