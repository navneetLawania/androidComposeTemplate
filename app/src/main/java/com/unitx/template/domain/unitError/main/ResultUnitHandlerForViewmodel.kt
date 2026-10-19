package com.unitx.template.domain.unitError.main

import com.unitx.template.domain.unitError.errors.ErrorBlueprint
import com.unitx.template.domain.unitError.errors.SuccessMessage
import com.unitx.template.domain.unitError.signal.BuiltSignal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

interface ResultUnitHandlerForViewmodel {
    val trigger: Channel<TriggerWrapper>

    suspend fun <D, E : ErrorBlueprint> handleResultUnit(
        result: ResultUnit<D, E>,
        onSuccessMessageCode: SuccessMessage = SuccessMessage.Void,
        onSuccess: suspend (D) -> Unit = {},
        onErrorAppend: (suspend (E) -> Unit)? = null,
    ) {
        when (result) {
            is ResultUnit.SuccessUnit -> {
                onSuccess(result.data)
                val message =
                    BuiltSignal.signalUI(onSuccessMessageCode)
                if (message != "RETURN") {
                    trigger.send(TriggerWrapper.MessageOn(message))
                }
            }
            is ResultUnit.ErrorUnit -> {
                val errorMessage = BuiltSignal.signalUI(result.error)
                trigger.send(TriggerWrapper.MessageOn(errorMessage))
                onErrorAppend?.invoke(result.error)
            }
        }
    }

    fun <T, E: ErrorBlueprint> returnResultFlow(
        result: ResultUnit<Flow<T>, E>,
        defaultValue: T
    ): Flow<T> {
        return flow {
            handleResultUnit(
                result = result,
                onSuccessMessageCode = SuccessMessage.Void,
                onSuccess = { flowData ->
                    emitAll(flowData)
                },
                onErrorAppend = {
                    emit(defaultValue)
                }
            )
        }
    }

    private suspend fun <D, E: ErrorBlueprint> handleResultWithNoTriggers(
        result: ResultUnit<D, E>,
        onSuccess: suspend (D) -> Unit,
        onError: suspend (e: ErrorBlueprint) -> Unit = { }
    ) {
        when (result) {
            is ResultUnit.SuccessUnit -> onSuccess(result.data)
            is ResultUnit.ErrorUnit -> onError(result.error)
        }
    }

    suspend fun <E : ErrorBlueprint> handleResultUnitJobs(
        vararg jobs: suspend () -> ResultUnit<*, E>,
        onSuccess: suspend () -> Unit = {}
    ) {
        for (job in jobs) {
            val result = job()
            var success = true
            handleResultUnit(result = result, onErrorAppend = { success = false })
            if (!success) return
        }
        onSuccess()
    }

    suspend fun <E : ErrorBlueprint> handleResultUnitParallel(
        scope: CoroutineScope,
        vararg jobs: suspend () -> ResultUnit<*, E>,
        onSuccess: suspend () -> Unit = {}
    ) {
        val results = scope.async {
            jobs.map { async { it() } }.awaitAll()
        }.await()
        var allSuccess = true
        results.forEach { result ->
            handleResultUnit(result = result, onErrorAppend = { allSuccess = false })
        }
        if (allSuccess) onSuccess()
    }
}