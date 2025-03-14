package ru.amalkoott.rx_example.examples.coldstream

import io.reactivex.rxjava3.core.Completable
import kotlin.random.Random

fun dataSourceCompletable() : Completable{
    return Completable.create{ subscriber ->
        if (Random.nextBoolean()) subscriber.onComplete() else subscriber.onError(Exception("Completable exception"))
    }
}