package com.unitx.template.presentation.navigation.route

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface TemplateRoute: NavKey {
    @Serializable
    data object HomeScreenKey: NavKey, TemplateRoute
    @Serializable
    data class SecondScreenKey(val id: String): NavKey, TemplateRoute
}