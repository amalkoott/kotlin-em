package ru.amalkoott.rx_example.utils

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.IOException
import java.sql.Time
import java.util.concurrent.TimeUnit
import kotlin.random.Random

typealias RequestResult = List<String>

// сервер с какими-то данными
fun getData() : Single<RequestResult> {
    return Single.create{ subscriber ->
        Thread.sleep(3000)

        if (Random.nextBoolean()) {
            val result = listOf("у меня осталось","две поездки","на следующий автобус", "вон он", "НА ПОДХОДЕ", "я буду дома", "вовремя!")
            subscriber.onSuccess(result)
        } else subscriber.onError(IOException("Some trouble..."))

    }.subscribeOn(Schedulers.io())
}

// первый сервер с картами
fun fromFirstServer() : Single<RequestResult>{
    return Single.create{ subscriber ->
        val cards = mutableListOf<String>()
        for (i in 0..9) cards.add("$i".repeat(10))
        subscriber.onSuccess(cards)
    }
}

// второй сервер с картами
fun fromSecondServer() : Single<RequestResult>{
    return Single.create{ subscriber ->
        val cards = mutableListOf<String>()
        for (i in 'A'..'E') cards.add("$i".repeat(10))
        subscriber.onSuccess(cards)
    }
}

// имитация ошибки с сервера
fun fromErrorServer() : Single<RequestResult>{
    return Single.create{ subscriber ->
        subscriber.onError(Exception())
    }
}

fun getReduceData() : Observable<Long>{
    return Observable.interval(1000,TimeUnit.MILLISECONDS)
        .scan{
            a, b -> a + b
        }
}
