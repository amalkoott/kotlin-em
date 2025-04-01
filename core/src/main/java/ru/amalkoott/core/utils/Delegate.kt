package ru.amalkoott.core.utils

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.amalkoott.core.utils.Constant.CACHE_PREFS
import ru.amalkoott.core.utils.Constant.KOTLIN_TASK
import ru.amalkoott.core.utils.Constant.LAUNCH_TIME_KEY
import ru.amalkoott.core.utils.Constant.LAUNCH_TIME_LOG_PATTERN
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class AppCache private constructor(context: Context) {
    var launhTime: Long by cache(context)

    init {
        launhTime = System.currentTimeMillis()
        CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                Log.d(
                    KOTLIN_TASK,
                    "${formatTime(launhTime)} | THREAD: ${Thread.currentThread().name}"
                )
                delay(3000)
            }
        }
    }

    companion object{
        @Volatile
        private var instance: AppCache? = null

        fun getInstance(context: Context): AppCache {
            return instance ?: synchronized(this){
                instance ?: AppCache(context).also { instance = it }
            }
        }
    }

    private fun cache(context: Context): ReadWriteProperty<Any?, Long> = object : ReadWriteProperty<Any?, Long> {
        private val sharedPref = context.getSharedPreferences(CACHE_PREFS, Context.MODE_PRIVATE)
        private val key = LAUNCH_TIME_KEY

        private var launchTime: Long? = null
        override fun getValue(thisRef: Any?, property: KProperty<*>): Long {
            if (launchTime == null) {
                launchTime = sharedPref.getLong(key, 0L)
            }
            return launchTime ?: throw IllegalStateException("launchTime is null")
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) {
            sharedPref.edit().putLong(key, value).apply()
        }
    }
}

fun formatTime(milliseconds: Long): String {
    val instant = Instant.ofEpochMilli(milliseconds)
    val formatter = DateTimeFormatter.ofPattern(LAUNCH_TIME_LOG_PATTERN)
        .withZone(ZoneId.systemDefault())
    return formatter.format(instant)
}

