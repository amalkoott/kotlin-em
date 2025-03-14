package ru.amalkoott.rx_example.examples.coldstream

import android.util.Log
import io.reactivex.rxjava3.core.Single
import ru.amalkoott.rx_example.TAG
import kotlin.random.Random


val single = Single.just(1)
val disposeSingle = single.subscribe({
    Log.e(TAG,"$it")
}, { })

fun dataSourceSingle() : Single<List<Int>>{
    return Single.create{ subscriber ->
        if (Random.nextBoolean()) subscriber.onSuccess(listOf(8,6,4,2)) else subscriber.onError(Exception())
    }
}