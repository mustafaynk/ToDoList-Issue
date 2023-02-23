package com.example.todolistziro

import android.app.Activity
import android.view.Window
import android.view.WindowManager

fun Activity.fullscreen() {
    window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN
    )
}