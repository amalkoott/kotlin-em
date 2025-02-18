package ru.amalkoott.kotlinem

import android.app.Application
import ru.amalkoott.kotlinem.utils.AppCache

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        AppCache.getInstance(applicationContext)
    }
}