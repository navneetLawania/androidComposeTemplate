package com.unitx.template.domain.unitError.mapper

import android.database.sqlite.SQLiteAbortException
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabaseLockedException
import android.database.sqlite.SQLiteException
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.IOException
import com.unitx.template.domain.unitError.errors.BuiltErrors
import com.unitx.template.domain.unitError.main.ResultUnit
import com.squareup.moshi.JsonDataException
import retrofit2.HttpException
import timber.log.Timber
import java.net.MalformedURLException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException
import javax.net.ssl.SSLException

// ExceptionMapper.kt
object ExceptionMapper {

    private fun logMappedError(source: String, errorType: Any, e: Exception) {
        Timber.tag(source).e(e, "$errorType")
    }

    fun <D> mapDataStoreError(
        exceptionState: ExceptionState, e: Exception, source: String
    ): ResultUnit.ErrorUnit<D, BuiltErrors.DataStoreError> {
        if (e is CancellationException) throw e
        if (exceptionState == ExceptionState.THROW) throw e
        val errorType = when (e) {
            is CorruptionException -> BuiltErrors.DataStoreError.Corruption
            is IOException -> BuiltErrors.DataStoreError.IOError
            is SecurityException -> BuiltErrors.DataStoreError.PermissionDenied
            is NullPointerException -> BuiltErrors.DataStoreError.NullValue
            else -> BuiltErrors.DataStoreError.Unknown
        }
        if (exceptionState == ExceptionState.LOG) logMappedError(source, errorType, e)
        return ResultUnit.ErrorUnit(errorType)
    }

    fun <D> mapRoomError(
        exceptionState: ExceptionState, e: Exception, source: String
    ): ResultUnit.ErrorUnit<D, BuiltErrors.RoomError> {
        if (e is CancellationException) throw e
        if (exceptionState == ExceptionState.THROW) throw e
        val errorType = when (e) {
            is SQLiteConstraintException -> BuiltErrors.RoomError.FOREIGN_KEY
            is JsonDataException -> BuiltErrors.RoomError.JSON_SYNTAX
            is SQLiteDatabaseLockedException -> BuiltErrors.RoomError.DATABASE_LOCKED
            is SQLiteAbortException -> BuiltErrors.RoomError.SQLITE_ABORT
            is NullPointerException -> BuiltErrors.RoomError.NULL_POINTER
            is IllegalStateException -> BuiltErrors.RoomError.ILLEGAL_STATE
            is SQLiteException -> BuiltErrors.RoomError.SQLITE_EXCEPTION
            else -> BuiltErrors.RoomError.UNKNOWN
        }
        if (exceptionState == ExceptionState.LOG) logMappedError(source, errorType, e)
        return ResultUnit.ErrorUnit(errorType)
    }

    fun <D> mapNetworkError(
        exceptionState: ExceptionState, e: Exception, source: String
    ): ResultUnit.ErrorUnit<D, BuiltErrors.NetworkError> {
        if (e is CancellationException) throw e
        if (exceptionState == ExceptionState.THROW) throw e
        val errorType = when (e) {
            is SocketTimeoutException -> BuiltErrors.NetworkError.TIMEOUT
            is SSLException -> BuiltErrors.NetworkError.SSL_ERROR
            is MalformedURLException -> BuiltErrors.NetworkError.MALFORMED_URL
            is JsonDataException -> BuiltErrors.NetworkError.JSON_PARSING_ERROR
            is IllegalStateException -> BuiltErrors.NetworkError.UNEXPECTED_RESPONSE
            is UnknownHostException -> BuiltErrors.NetworkError.NO_INTERNET
            else -> BuiltErrors.NetworkError.UNKNOWN
        }
        if (exceptionState == ExceptionState.LOG) logMappedError(source, errorType, e)
        return ResultUnit.ErrorUnit(errorType)
    }

    fun <D> mapHttpError(
        exceptionState: ExceptionState, e: HttpException, source: String
    ): ResultUnit.ErrorUnit<D, BuiltErrors.NetworkError> {
        if (exceptionState == ExceptionState.THROW) throw e
        val httpError = when (e.code()) {
            400 -> BuiltErrors.NetworkError.HTTP_ERROR
            401 -> BuiltErrors.NetworkError.UNAUTHORIZED
            403 -> BuiltErrors.NetworkError.FORBIDDEN
            404 -> BuiltErrors.NetworkError.RESOURCE_NOT_FOUND
            408 -> BuiltErrors.NetworkError.TIMEOUT
            429 -> BuiltErrors.NetworkError.TOO_MANY_REQUESTS
            in 500..599 -> BuiltErrors.NetworkError.SERVER_ERROR
            else -> BuiltErrors.NetworkError.HTTP_ERROR
        }
        if (exceptionState == ExceptionState.LOG) logMappedError(source, httpError, e)
        return ResultUnit.ErrorUnit(httpError)
    }

    inline fun <D> room(
        exceptionState: ExceptionState,
        source: String,
        action: () -> D
    ): ResultUnit<D, BuiltErrors.RoomError> =
        try {
            ResultUnit.SuccessUnit(action())
        } catch (e: Exception) {
            mapRoomError(exceptionState, e, source)
        }

    inline fun <D> dataStore(
        exceptionState: ExceptionState,
        source: String,
        action: () -> D
    ): ResultUnit<D, BuiltErrors.DataStoreError> =
        try {
            ResultUnit.SuccessUnit(action())
        } catch (e: Exception) {
            mapDataStoreError(exceptionState, e, source)
        }

    inline fun <D> network(
        exceptionState: ExceptionState,
        source: String,
        action: () -> D
    ): ResultUnit<D, BuiltErrors.NetworkError> =
        try {
            ResultUnit.SuccessUnit(action())
        } catch (e: HttpException) {
            mapHttpError(exceptionState, e, source)
        } catch (e: Exception) {
            mapNetworkError(exceptionState, e, source)
        }
}