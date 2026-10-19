package com.unitx.template.di

import com.squareup.moshi.Moshi
import com.unitx.template.data.moshi.MoshiClient
import org.koin.dsl.module

val appModule = module {
    includes(
        databaseModule,
        datastoreModule,
        networkModule,
        repoModule,
        viewmodelModule
    )

    single<Moshi> { MoshiClient.getInstance() }
}