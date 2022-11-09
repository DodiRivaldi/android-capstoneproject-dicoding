package com.dodi.core.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class PreferencesImpl(context: Context):Preferences {
    private lateinit var sharedPreferences : SharedPreferences
    private val prefName = "samplePrefName"
    private val privateMode = 0

    companion object{
        const val IS_LOGIN = "isLogin"
        const val TOKEN = "spToken"
    }

    init {
        sharedPreferences = context.getSharedPreferences(prefName,privateMode)
    }

    override fun saveSpString(key: String, value: String) {
        sharedPreferences.edit { putString(key,value) }
    }

    override fun saveSpBoolean(key: String, value: Boolean) {
        sharedPreferences.edit { putBoolean(key,value) }
    }

    override fun saveSpInt(key: String, value: Int) {
        sharedPreferences.edit { putInt(key,value) }
    }

    override fun getIsLogin(): Boolean {
        return sharedPreferences.getBoolean(IS_LOGIN,false)
    }

    override fun getToken(): String? {
        return sharedPreferences.getString(TOKEN,"")
    }
}