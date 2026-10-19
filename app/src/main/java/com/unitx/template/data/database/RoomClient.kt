package com.unitx.template.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.unitx.template.data.database.converter.UserConvertor
import com.unitx.template.data.database.dao.UserDao
import com.unitx.template.data.database.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    UserConvertor::class,
)
abstract class RoomClient : RoomDatabase() {

    abstract fun VehicleDao(): UserDao

    companion object {
        private var INSTANCE: RoomClient? = null

        fun getDatabase(context: Context): RoomClient {
            return INSTANCE ?: synchronized(this) {
                val instance = Room
                    .databaseBuilder(context, RoomClient::class.java, "fikkerDB")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}