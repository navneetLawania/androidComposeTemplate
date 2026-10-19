package com.unitx.template.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.unitx.template.data.datastore.DatastoreClient
import org.koin.dsl.module

val datastoreModule = module {
    single<DataStore<Preferences>> {
        DatastoreClient.getInstance(get())
    }
}