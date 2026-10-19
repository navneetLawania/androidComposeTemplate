package com.unitx.template.domain.unitError.main


sealed interface TriggerWrapper {
    data object LoadingOn: TriggerWrapper
    data object LoadingOff : TriggerWrapper
    data class MessageOn(val message: String): TriggerWrapper
}