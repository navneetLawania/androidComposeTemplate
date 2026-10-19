package com.unitx.template.data.network

import com.unitx.template.domain.model.User

data class UserDto(
    val id: String
){
    fun toDomain(): User = User(id = this.id)
}