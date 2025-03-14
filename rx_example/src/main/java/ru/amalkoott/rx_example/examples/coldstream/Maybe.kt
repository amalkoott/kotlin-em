package ru.amalkoott.rx_example.examples.coldstream

import io.reactivex.rxjava3.core.Maybe
import kotlin.random.Random

fun dataSourceMaybe() : Maybe<List<Int>>{
    return Maybe.create{ subscriber ->
        val list = List(10) { Random.nextInt(0, 10) }

        // имитируем разные данные

        // какие-то некорректные данные или ошибка
        if (list.contains(7)) subscriber.onError(IllegalStateException("List contains 7!!!"))

        // не пустые данные (сценарий, когда данные важны и мы их возвращаем)
        if (list.contains(4)) subscriber.onSuccess(list)

        // метод отработал, но по какой-то причине мы просто оповещаем об успехе (без данных!)
        subscriber.onComplete()
    }
}