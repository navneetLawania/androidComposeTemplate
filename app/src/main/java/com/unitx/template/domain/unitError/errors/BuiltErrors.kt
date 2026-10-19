package com.unitx.template.domain.unitError.errors

object BuiltErrors {

//    enum class FirestoreError() : ErrorBlueprint {
//        PERMISSION_DENIED,       // Security rules rejection
//        UNAUTHENTICATED,         // Not signed in
//        RESOURCE_NOT_FOUND,      // Document not found
//        ALREADY_EXISTS,          // Duplicate write conflict
//        ABORTED,                 // Transaction aborted
//        UNAVAILABLE,             // Firestore unavailable
//        CANCELLED,               // Operation cancelled
//        DEADLINE_EXCEEDED,       // Timeout
//        INTERNAL,                // Internal Firestore error
//        DATA_LOSS,               // Corruption
//        UNKNOWN,                 // Unknown Firebase error
//        NETWORK_ERROR,           // Internet issues (FirebaseNetworkException)
//        API_NOT_AVAILABLE,       // API not present on device
//        INVALID_ARGUMENT         // Bad data passed
//        ;
//    }

    enum class RoomError() : ErrorBlueprint {
        FOREIGN_KEY,
        JSON_SYNTAX,
        SQLITE_EXCEPTION,
        SQLITE_ABORT,
        NULL_POINTER,
        ILLEGAL_STATE,
        DATABASE_LOCKED,
        UNKNOWN,
    }

    enum class DataStoreError(): ErrorBlueprint {
        IOError,
        Corruption,
        PermissionDenied,
        NullValue,
        Unknown
    }

    enum class NetworkError() :
        ErrorBlueprint {
        NETWORK_ERROR,      // General network issue
        TIMEOUT,            // Request timeout
        HTTP_ERROR,         // Generic HTTP error
        JSON_SYNTAX,        // JSON format issue
        NULL_BODY,          // Empty or null response
        UNKNOWN,            // Unknown error
        MALFORMED_URL,      // Invalid URL format
        SSL_ERROR,          // SSL certificate issues
        UNAUTHORIZED,       // 401 Unauthorized error
        FORBIDDEN,          // 403 Forbidden error
        RESOURCE_NOT_FOUND, // 404 Not Found error
        SERVER_ERROR,       // 500+ Server-side error
        TOO_MANY_REQUESTS,  // 429 Rate limit exceeded
        JSON_PARSING_ERROR, // Error while parsing JSON
        EMPTY_RESPONSE,     // Response body is empty
        UNEXPECTED_RESPONSE, // Response structure is unexpected
        NO_INTERNET
    }
}