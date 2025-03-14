package ru.amalkoott.example.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

fun <T> Flow<T>.throttleLast(delay: Long, timeUnit: TimeUnit = TimeUnit.MILLISECONDS): Flow<T> = flow{
    val channel = Channel<T>(Channel.UNLIMITED)

    var willEmitValue : T? = null
    CoroutineScope(Dispatchers.Default).launch {
        this@throttleLast.collect {
            willEmitValue = it
        }
        channel.close()
    }

    CoroutineScope(Dispatchers.Default).launch {
        while (true) {
            delay(timeUnit.toMillis(delay))
            willEmitValue?.let {
                channel.send(it)
            }
        }
    }

    for (value in channel) {
        emit(value)
    }
}

fun <T> Flow<T>.lateThrottleLast(delay: Long = 0, timeUnit: TimeUnit = TimeUnit.MILLISECONDS): Flow<T> {
    var emissionTime = 0L
    var preValue: T? = null

    return flow {
        collect{
            val currentTime = System.currentTimeMillis()
            if(currentTime - emissionTime >= timeUnit.toMillis(delay)){
                preValue?.let {
                        value -> emit(value)
                }
                emissionTime = currentTime

            }else{
                preValue = it
            }
        }
    }
}

fun <T> Flow<T>.throttleFirst(delay: Long = 0, timeUnit: TimeUnit = TimeUnit.MILLISECONDS): Flow<T> {
    var emissionTime = 0L

    return flow {
        collect{
            val  currentTime = System.currentTimeMillis()
            if(currentTime - emissionTime >= timeUnit.toMillis(delay)){
                emit(it)
                emissionTime = currentTime
            }
        }
    }
}