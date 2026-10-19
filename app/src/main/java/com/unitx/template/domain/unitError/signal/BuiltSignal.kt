package com.unitx.template.domain.unitError.signal

import com.unitx.template.domain.unitError.errors.BuiltErrors
import com.unitx.template.domain.unitError.errors.ErrorBlueprint

object BuiltSignal {

    fun void() = "RETURN"

    fun signalUI(point: ErrorBlueprint): String {
        return when(point) {
            is BuiltErrors.RoomError -> roomSignal(point)
            is BuiltErrors.DataStoreError -> datastoreSignal(point)
            is BuiltErrors.NetworkError -> networkSignal(point)
//            is BuiltErrors.FirestoreError -> firestoreSignal(point)
            else -> void()
        }
    }

    private fun roomSignal(point: BuiltErrors.RoomError) =
        when (point) {
            BuiltErrors.RoomError.FOREIGN_KEY -> "Operation failed due to missing related data."
            BuiltErrors.RoomError.JSON_SYNTAX -> "Invalid data format detected."
            BuiltErrors.RoomError.SQLITE_EXCEPTION -> "A database error occurred. Please try again."
            BuiltErrors.RoomError.SQLITE_ABORT -> "The operation was interrupted. Try again later."
            BuiltErrors.RoomError.NULL_POINTER -> "Unexpected empty value encountered."
            BuiltErrors.RoomError.ILLEGAL_STATE -> "Database version mismatch. A migration may be needed."
            BuiltErrors.RoomError.DATABASE_LOCKED -> "The database is currently in use. Please wait."
            BuiltErrors.RoomError.UNKNOWN -> "An unknown error occurred. Please contact support. Room"
        }

    private fun datastoreSignal(point: BuiltErrors.DataStoreError) =
        when (point) {
            BuiltErrors.DataStoreError.IOError -> "Failed to read or write data."
            BuiltErrors.DataStoreError.Corruption -> "Data corruption detected."
            BuiltErrors.DataStoreError.PermissionDenied -> "Permission denied for persistent storage."
            BuiltErrors.DataStoreError.NullValue -> "An unexpected error occurred."
            BuiltErrors.DataStoreError.Unknown -> "An unexpected error occurred."
        }

    private fun networkSignal(point: BuiltErrors.NetworkError) =
        when (point) {
            BuiltErrors.NetworkError.NETWORK_ERROR -> "Network error. Please try again."
            BuiltErrors.NetworkError.TIMEOUT -> "Request timed out. Check your connection."
            BuiltErrors.NetworkError.HTTP_ERROR -> "Unexpected server response. Try again later."
            BuiltErrors.NetworkError.JSON_SYNTAX -> "Data error. Please update the app."
            BuiltErrors.NetworkError.NULL_BODY -> "No response from server. Try again later."
            BuiltErrors.NetworkError.UNKNOWN -> "Something went wrong. Please try again."
            BuiltErrors.NetworkError.MALFORMED_URL -> "Invalid request. Contact support."
            BuiltErrors.NetworkError.SSL_ERROR -> "Security error. Check your network."
            BuiltErrors.NetworkError.UNAUTHORIZED -> "Unauthorized access. Please log in."
            BuiltErrors.NetworkError.FORBIDDEN -> "Access denied. Contact support."
            BuiltErrors.NetworkError.RESOURCE_NOT_FOUND -> "Resource not found. Try again later."
            BuiltErrors.NetworkError.SERVER_ERROR -> "Server error. Please try again later."
            BuiltErrors.NetworkError.TOO_MANY_REQUESTS -> "Too many requests. Slow down!"
            BuiltErrors.NetworkError.JSON_PARSING_ERROR -> "Data processing error. Try again."
            BuiltErrors.NetworkError.EMPTY_RESPONSE -> "No data received. Try again later."
            BuiltErrors.NetworkError.UNEXPECTED_RESPONSE -> "Unexpected response. Try again."
            BuiltErrors.NetworkError.NO_INTERNET -> "No internet connection"
        }

//    private fun firestoreSignal(point: BuiltErrors.FirestoreError): String {
//        return when (point) {
//            BuiltErrors.FirestoreError.PERMISSION_DENIED -> "You don't have permission to perform this action."
//            BuiltErrors.FirestoreError.UNAUTHENTICATED -> "You are not logged in. Please sign in to continue."
//            BuiltErrors.FirestoreError.RESOURCE_NOT_FOUND -> "Requested data was not found on the server."
//            BuiltErrors.FirestoreError.ALREADY_EXISTS -> "This record already exists in the cloud."
//            BuiltErrors.FirestoreError.ABORTED -> "Operation was aborted due to a conflict or transaction failure."
//            BuiltErrors.FirestoreError.UNAVAILABLE -> "Cloud service is temporarily unavailable. Try again later."
//            BuiltErrors.FirestoreError.CANCELLED -> "The request was cancelled. Please retry if needed."
//            BuiltErrors.FirestoreError.DEADLINE_EXCEEDED -> "The operation took too long. It timed out."
//            BuiltErrors.FirestoreError.INTERNAL -> "Internal server error occurred. We're working on it."
//            BuiltErrors.FirestoreError.DATA_LOSS -> "Critical data loss occurred. Operation failed."
//            BuiltErrors.FirestoreError.UNKNOWN -> "An unknown cloud error occurred."
//            BuiltErrors.FirestoreError.NETWORK_ERROR -> "No internet connection or network error."
//            BuiltErrors.FirestoreError.API_NOT_AVAILABLE -> "Firebase API is not available on this device."
//            BuiltErrors.FirestoreError.INVALID_ARGUMENT -> "The data provided was invalid. Please check and retry."
//        }
//    }
}