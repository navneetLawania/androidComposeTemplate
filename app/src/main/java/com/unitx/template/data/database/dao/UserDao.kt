package com.unitx.template.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.unitx.template.data.database.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userEntity: UserEntity)

    @Delete
    suspend fun delete(userEntity: UserEntity)

    @Update
    suspend fun update(userEntity: UserEntity)

    @Query("SELECT * FROM UserEntity")
    suspend fun getAll(): List<UserEntity>
}