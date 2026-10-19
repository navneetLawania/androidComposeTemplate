package com.unitx.template.data.constant

import androidx.datastore.preferences.core.stringPreferencesKey

enum class DatastoreConstant {
    USER_ID;

    companion object{
        val KEY_USER_ID = stringPreferencesKey(USER_ID.name)
    }
}