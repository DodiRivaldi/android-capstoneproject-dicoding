package com.dodi.core.data.preferences

interface Preferences {
    fun saveSpString(key : String, value : String)
    fun saveSpBoolean(key : String, value : Boolean)
    fun saveSpInt(key : String, value : Int)

    fun getIsLogin(): Boolean
    fun getToken(): String?
}