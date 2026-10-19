package com.unitx.template.di

import com.unitx.template.data.repository.UserRepository
import com.unitx.template.presentation.mvi.UserViewmodel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {
    viewModel { UserViewmodel(get<UserRepository>()) }
}