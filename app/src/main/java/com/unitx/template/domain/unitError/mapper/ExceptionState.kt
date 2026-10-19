package com.unitx.template.domain.unitError.mapper

enum class ExceptionState {
    CATCH,
    LOG,
    THROW;
    companion object {
        val default: ExceptionState = THROW
    }
}