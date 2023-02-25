package com.example.todolistziro.architecture

import android.app.Application
import android.content.Context
import com.example.todolistziro.architecture.di.ApiComponent
import dagger.hilt.EntryPoints
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    val apiComponent: ApiComponent
        get() = EntryPoints.get(this, ApiComponent::class.java)

    override fun onCreate() {
        super.onCreate()
        instance = this
        appContext = applicationContext
    }
    companion object {
        lateinit var instance: App
            private set

        lateinit var appContext: Context
            private set
    }
}