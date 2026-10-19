package com.unitx.template.domain.unitError.main

import com.unitx.template.domain.unitError.errors.ErrorBlueprint
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

abstract class ResultUnitHandlerForRepo {
    suspend inline fun <D, E : ErrorBlueprint, R> ResultUnit<D, E>.onSuccess(
        block: suspend (D) -> ResultUnit<R, E>
    ): ResultUnit<R, E> {
        return when (this) {
            is ResultUnit.SuccessUnit -> block(data)
            is ResultUnit.ErrorUnit -> ResultUnit.ErrorUnit(error)
        }
    }

    inline fun <A, B, R, E : ErrorBlueprint> combine(
        a: ResultUnit<A, E>,
        b: ResultUnit<B, E>,
        transform: (A, B) -> R
    ): ResultUnit<R, E> = when {
        a is ResultUnit.ErrorUnit -> ResultUnit.ErrorUnit(a.error)
        b is ResultUnit.ErrorUnit -> ResultUnit.ErrorUnit(b.error)
        a is ResultUnit.SuccessUnit && b is ResultUnit.SuccessUnit ->
            ResultUnit.SuccessUnit(transform(a.data, b.data))
        else -> error("unreachable")
    }

    suspend inline fun <A, B, R, E : ErrorBlueprint> combine(
        crossinline a: suspend () -> ResultUnit<A, E>,
        crossinline b: suspend () -> ResultUnit<B, E>,
        crossinline transform: (A, B) -> R
    ): ResultUnit<R, E> = coroutineScope {
        val da = async { a() }
        val db = async { b() }
        combine(da.await(), db.await(), transform)
    }

    suspend inline fun <E : ErrorBlueprint> runJobs(
        vararg jobs: suspend () -> ResultUnit<*, E>
    ): ResultUnit<Unit, E> {
        for (job in jobs) {
            when (val result = job()) {
                is ResultUnit.SuccessUnit -> continue
                is ResultUnit.ErrorUnit -> {
                    return ResultUnit.ErrorUnit(result.error)
                }
            }
        }
        return ResultUnit.SuccessUnit(Unit)
    }

    suspend inline fun <A, B, R, E : ErrorBlueprint> combineFlow(
        crossinline a: suspend () -> ResultUnit<Flow<A>, E>,
        crossinline b: suspend () -> ResultUnit<Flow<B>, E>,
        noinline transform: suspend (A, B) -> R
    ): ResultUnit<Flow<R>, E> = coroutineScope {
        val da = async { a() }
        val db = async { b() }
        val ra = da.await()
        val rb = db.await()
        when {
            ra is ResultUnit.ErrorUnit -> ResultUnit.ErrorUnit(ra.error)
            rb is ResultUnit.ErrorUnit -> ResultUnit.ErrorUnit(rb.error)
            ra is ResultUnit.SuccessUnit && rb is ResultUnit.SuccessUnit ->
                ResultUnit.SuccessUnit(combine(ra.data, rb.data, transform))
            else -> error("unreachable")
        }
    }
}