package com.unitx.template.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import java.util.UUID

@Entity
@JsonClass(generateAdapter = true)
data class UserEntity(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
)