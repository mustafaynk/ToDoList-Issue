package com.example.todolistziro.architecture.extensions

import android.content.Context
import androidx.fragment.app.Fragment

fun Fragment.getStringDataToLocal(key: String): String {
    return requireActivity().getPreferences(Context.MODE_PRIVATE).getString(key, String()).toString()
}