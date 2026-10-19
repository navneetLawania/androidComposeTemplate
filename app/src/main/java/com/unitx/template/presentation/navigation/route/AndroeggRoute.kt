package com.unitx.template.presentation.navigation.route

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface AndroeggRoute: NavKey {
    @Serializable
    data object HomeScreenKey: NavKey, AndroeggRoute
    @Serializable
    data class SecondScreenKey(val id: String): NavKey, AndroeggRoute
}