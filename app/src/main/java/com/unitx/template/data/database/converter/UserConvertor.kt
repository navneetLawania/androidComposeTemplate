package com.unitx.template.data.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.unitx.template.data.database.entity.UserEntity
import com.squareup.moshi.Moshi

@ProvidedTypeConverter
class UserConvertor(private val moshi: Moshi) {

    @TypeConverter
    fun fromUserEntity(vehicle: UserEntity): String {
        return moshi.adapter(UserEntity::class.java).toJson(vehicle)
    }

    @TypeConverter
    fun toUserEntity(json: String): UserEntity? {
        return moshi.adapter(UserEntity::class.java).fromJson(json)
    }
}