package com.n1njac.weread.utils

import android.content.Context
import android.preference.PreferenceManager

/*    
 *    Created by N1njaC on 2018/5/13.
 *    email:aiai173cc@gmail.com 
 */
object PreferenceUtils {

    fun putSP(key: String, value: Any, context: Context) {
        val type = value.javaClass.simpleName
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sharedPreferences.edit()
        when (type) {
            "Integer" -> editor.putInt(key, value as Int)
            "Boolean" -> editor.putBoolean(key, value as Boolean)
            "String" -> editor.putString(key, value as String)
            "Float" -> editor.putFloat(key, value as Float)
            "Long" -> editor.putLong(key, value as Long)
        }
        editor.apply()
    }

    fun getSP(key: String, defValue: Any, context: Context): Any? {
        val type = defValue.javaClass.simpleName
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return when (type) {
            "Integer" -> sharedPreferences.getInt(key, defValue as Int)
            "Boolean" -> sharedPreferences.getBoolean(key, defValue as Boolean)
            "String" -> sharedPreferences.getString(key, defValue as String)
            "Float" -> sharedPreferences.getFloat(key, defValue as Float)
            "Long" -> sharedPreferences.getLong(key, defValue as Long)
            else -> null
        }
    }
}