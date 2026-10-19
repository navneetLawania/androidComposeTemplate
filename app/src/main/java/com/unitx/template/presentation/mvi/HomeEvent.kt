package com.unitx.template.presentation.mvi

sealed interface HomeEvent {
    data class ScreenChange(val id: String): HomeEvent

}