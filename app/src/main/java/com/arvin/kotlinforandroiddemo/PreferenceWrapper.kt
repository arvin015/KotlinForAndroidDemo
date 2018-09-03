package com.arvin.kotlinforandroiddemo

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PreferenceWrapper<T>(val context: Context, val key: String?, val value: T) :
        ReadWriteProperty<Any?, T> {

    val pref: SharedPreferences by lazy { context.getSharedPreferences("KontlinForAndroid", Context.MODE_PRIVATE) }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {

        lateinit var rs: Any
        with(pref) {
            rs = when (value) {
                is String -> getString(key, value)
                is Long -> getLong(key, value)
                is Boolean -> getBoolean(key, value)
                is Int -> getInt(key, value)
                is Float -> getFloat(key, value)
                else -> throw IllegalArgumentException("This type can't be saved into Preferences")
            }
        }

        return rs as T
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        with(pref.edit()) {
            when (value) {
                is String -> putString(key, value)
                is Long -> putLong(key, value)
                is Boolean -> putBoolean(key, value)
                is Int -> putInt(key, value)
                is Float -> putFloat(key, value)
                else -> throw IllegalArgumentException("This type can't be saved into Preferences")
            }
            commit()
        }
    }

}