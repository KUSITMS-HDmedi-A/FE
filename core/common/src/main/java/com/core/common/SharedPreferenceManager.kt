package com.core.common

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceManager(context:Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("HDMEDI_SHARED_PREFERENCE", 0)
    private var editor: SharedPreferences.Editor = prefs.edit()

    fun setFCMToken(token: String){
        editor.putString("FCM_TOKEN", token).apply()
    }

    fun getFCMToken(): String? =
        prefs.getString("FCM_TOKEN", null)

}