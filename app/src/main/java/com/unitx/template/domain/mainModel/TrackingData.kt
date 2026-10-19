package com.unitx.template.domain.mainModel

data class TrackingData(
    val deviceId: String,
    val latitude: Double,
    val longitude: Double,
    val timestamp: Long
)