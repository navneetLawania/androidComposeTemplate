package com.unitx.template.presentation.mvi

import androidx.lifecycle.ViewModel
import com.unitx.template.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UserViewmodel(
    private val userRepo: UserRepository
): ViewModel() {

    private var _state = MutableStateFlow<HomeState>(HomeState())
    val state get() = _state.asStateFlow()

    private fun screenChange(id: String) = _state.update { it.copy(id = id) }

    fun onEvent(event : HomeEvent){
        when(event){
            is HomeEvent.ScreenChange -> screenChange(event.id)
        }
    }

}