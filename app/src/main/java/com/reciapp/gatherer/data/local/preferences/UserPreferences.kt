package com.reciapp.gatherer.data.local.preferences

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.reciapp.gatherer.data.local.PreferencesHelper.get
import com.reciapp.gatherer.data.local.PreferencesHelper.set

class UserPreferences(
    private val preferences: SharedPreferences
) {

    var id: String
        get() = preferences[USER_ID, String()] ?: String()
        set(value) {
            preferences[USER_ID] = value
        }

    var lastName: String
        get() = preferences[USER_LAST_NAME, String()] ?: String()
        set(value) {
            preferences[USER_LAST_NAME] = value
        }

    var firstName: String
        get() = preferences[USER_FIRST_NAME, String()] ?: String()
        set(value) {
            preferences[USER_FIRST_NAME] = value
        }

    var type: String
        get() = preferences[USER_TYPE, String()] ?: String()
        set(value) {
            preferences[USER_TYPE] = value
        }

    @SuppressLint("ApplySharedPref")
    fun clear() {
        preferences.edit().clear().commit()
    }

    companion object {
        const val USER_ID = "user_id"
        const val USER_FIRST_NAME = "user_first_name"
        const val USER_LAST_NAME = "user_last_name"
        const val USER_TYPE = "user_type"
    }
}