package com.example.todolistziro.architecture.extensions

import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.view.WindowManager
import com.example.todolistziro.R

fun Activity.fullscreen() {
    window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN
    )
}

fun Activity.saveStringDataToLocal(key:String, value: String) {
    val sharedPref = getPreferences(MODE_PRIVATE) ?: return
    with (sharedPref.edit()) {
        putString(key, value)
        commit()
    }
}

fun Activity.getStringDataToLocal(key: String): String {
    val sharedPref = getPreferences(MODE_PRIVATE) ?: return String()
    return sharedPref.getString(key, String()) ?: String()
}