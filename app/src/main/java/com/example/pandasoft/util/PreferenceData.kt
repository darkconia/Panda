package com.example.pandasoft.util

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class PreferenceData (var context : Context) {

    private var prefs : SharedPreferences

    var accessToken = "accessToken"
    var refreshToken = "refreshToken"
    var expireIn = "expireIn"
    var preferenceData = "preferenceData"


    init {
        prefs = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun addShareConfig(key: String, data: Any) {
        prefs[key] = data

    }

    fun removeShareConfig(key: String) {
        prefs.edit().remove(key).commit()
    }

    fun getShareConfig(key: String): Any {

        when (key) {
            accessToken -> {
                return prefs[key , ""] as String
            }
            refreshToken -> {
                return prefs[key , ""]  as String
            }
            expireIn -> {
                return prefs[key, android.os.Build.VERSION.SDK_INT.toString()] as String
            }
        }
        return false
    }

}

private operator fun SharedPreferences.set(key: String, value: Any) {
    when (value) {
        is String? -> edit({ it.putString(key, value) })
        is Int -> edit({ it.putInt(key, value) })
        is Boolean -> edit({ it.putBoolean(key, value) })
        is Float -> edit({ it.putFloat(key, value) })
        is Long -> edit({ it.putLong(key, value) })
        else -> throw UnsupportedOperationException("Not yet implemented")
    }
}

operator inline fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T? {
    return when (T::class) {
        String::class -> getString(key, defaultValue as? String) as T?
        Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
        Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
        Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
        Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
        else -> throw UnsupportedOperationException("Not yet implemented")
    }
}

inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
    val editor = this.edit()
    operation(editor)
    editor.apply()
}
