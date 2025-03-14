package ru.amalkoott.kotlinem

import android.app.Application
import ru.amalkoott.example.utils.AppCache

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        ru.amalkoott.example.utils.AppCache.getInstance(applicationContext)
    }
}