package com.unitx.template.domain.unitError.main

import com.unitx.template.domain.unitError.errors.ErrorBlueprint

sealed interface ResultUnit<out D, out E: ErrorBlueprint> {
    data class SuccessUnit<out D, out E: ErrorBlueprint>(val data : D): ResultUnit<D, E>
    data class ErrorUnit<out D, out E: ErrorBlueprint>(val error: E): ResultUnit<D, E>
}