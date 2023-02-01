package com.yashkasera.musicwiki.util

import android.content.Context
import android.content.SharedPreferences
import com.yashkasera.musicwiki.AppObjectController

object PrefManager {


    @JvmStatic
    private val prefs: SharedPreferences by lazy {
        AppObjectController.musicWikiApplication.getSharedPreferences(
            AppObjectController.musicWikiApplication.packageName,
            Context.MODE_PRIVATE
        )
    }

    fun put(key: String, value: Any) {
        when (value) {
            is Int -> prefs.edit().putInt(key, value).apply()
            is String -> prefs.edit().putString(key, value).apply()
            is Boolean -> prefs.edit().putBoolean(key, value).apply()
            is Float -> prefs.edit().putFloat(key, value).apply()
            is Long -> prefs.edit().putLong(key, value).apply()
        }
    }

    fun getString(key: String, defValue: String? = null) = prefs.getString(key, defValue ?: EMPTY) ?: defValue ?: EMPTY

    fun getInt(key: String, defValue: Int? = null) = prefs.getInt(key, defValue ?: -1)

    fun getBoolean(key: String, defValue: Boolean? = null) = prefs.getBoolean(key, defValue ?: false)

    fun getFloat(key: String, defValue: Float? = null) = prefs.getFloat(key, defValue ?: 0f)

    fun getLong(key: String, defValue: Long? = null) = prefs.getLong(key, defValue ?: 0L)

    fun clear() {
        prefs.edit().clear().apply()
    }
}