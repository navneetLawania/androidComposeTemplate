package com.unitx.template.data.network

import com.unitx.template.domain.unitError.ObjectNetwork
import com.unitx.template.domain.unitError.mapper.ExceptionState
import com.unitx.template.domain.unitError.mapper.networkResult

class DropboxCloudSourceImpl(
    val service: DropboxService
) {
    suspend fun getUser(): ObjectNetwork<UserDto> =
        networkResult(ExceptionState.default) {
            service.getUser()
        }
}