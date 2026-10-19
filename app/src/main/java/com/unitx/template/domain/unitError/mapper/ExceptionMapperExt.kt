package com.unitx.template.domain.unitError.mapper

import com.unitx.template.domain.unitError.errors.BuiltErrors
import com.unitx.template.domain.unitError.main.ResultUnit


inline fun <D> Any.networkResult(
    exceptionState: ExceptionState,
    action: () -> D
): ResultUnit<D, BuiltErrors.NetworkError> =
    ExceptionMapper.network(exceptionState, this::class.java.simpleName, action)

inline fun <D> Any.roomResult(
    exceptionState: ExceptionState,
    action: () -> D
): ResultUnit<D, BuiltErrors.RoomError> =
    ExceptionMapper.room(exceptionState, this::class.java.simpleName, action)

inline fun <D> Any.dataStoreResult(
    exceptionState: ExceptionState,
    action: () -> D
): ResultUnit<D, BuiltErrors.DataStoreError> =
    ExceptionMapper.dataStore(exceptionState, this::class.java.simpleName, action)