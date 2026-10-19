package com.unitx.template.data.repository

import com.unitx.template.data.network.DropboxCloudSourceImpl
import com.unitx.template.domain.model.User
import com.unitx.template.domain.unitError.ObjectNetwork
import com.unitx.template.domain.unitError.main.ResultUnit
import com.unitx.template.domain.unitError.main.ResultUnitHandlerForRepo

// Handle all the data calls and expose only crud functions of the repo
// Manage crud of cloud and local here, only.
// Remember: Domain should not know where the data is coming from and where it is going.
class UserRepository(
    val dropboxCloudSourceImpl: DropboxCloudSourceImpl
) : ResultUnitHandlerForRepo() {
    suspend fun getTrackingData(deviceId: String): ObjectNetwork<User> =
        dropboxCloudSourceImpl.getUser().onSuccess { userDto ->
            ResultUnit.SuccessUnit(userDto.toDomain())
        }
}