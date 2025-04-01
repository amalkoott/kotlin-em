package ru.amalkoott.kotlinem

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        ru.amalkoott.core.utils.AppCache.getInstance(applicationContext)
    }
}