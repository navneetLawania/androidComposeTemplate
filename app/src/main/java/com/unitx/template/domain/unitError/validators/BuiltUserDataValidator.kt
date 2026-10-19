package com.unitx.template.domain.unitError.validators

interface BuiltUserDataValidator {
    fun isReservedText(text: String, reservedKeys: List<String>): Boolean {
        return text.trim().equals(
            reservedKeys.find { it.equals(text.trim(), ignoreCase = true) }, ignoreCase = true
        )
    }

    fun isDuplicate(text: String, existingWishlistName: List<String>): Boolean {
        return text.trim().equals(
            existingWishlistName.find { it.equals(text.trim(), ignoreCase = true) },
            ignoreCase = true
        )
    }

    fun emptyOrBlankAny(vararg texts: String): Boolean =
        texts.any { it.isEmpty() || it.isBlank() }

    fun emptyOrBlankAll(vararg texts: String): Boolean =
        texts.all { it.isEmpty() || it.isBlank() }
}