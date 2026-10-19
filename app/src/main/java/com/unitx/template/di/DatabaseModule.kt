package com.unitx.template.di

import androidx.room.RoomDatabase
import com.unitx.template.data.database.RoomClient
import com.unitx.template.data.database.converter.UserConvertor
import com.unitx.template.data.database.dao.UserDao
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {
    single<RoomDatabase> {
        RoomClient.getDatabase(get())
    }

    singleOf(::UserConvertor)

    single<UserDao> {
        get<RoomClient>().VehicleDao()
    }
}