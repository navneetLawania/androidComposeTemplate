package com.unitx.template.data.network

import retrofit2.http.GET

interface DropboxService {
    @GET
    suspend fun getUser(): UserDto
}