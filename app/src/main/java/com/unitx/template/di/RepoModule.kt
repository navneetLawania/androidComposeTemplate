package com.unitx.template.di

import com.unitx.template.data.repository.UserRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repoModule = module {
    singleOf(::UserRepository)
}