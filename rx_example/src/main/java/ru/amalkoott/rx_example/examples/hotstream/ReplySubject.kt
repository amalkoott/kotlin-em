package ru.amalkoott.rx_example.examples.hotstream

import android.util.Log
import io.reactivex.rxjava3.subjects.ReplaySubject
import ru.amalkoott.rx_example.TAG

fun replay() {
    Log.d(TAG,"Replay example 1:")
    val replay = ReplaySubject.create<Int>()
    replay.onNext(1)
    replay.onNext(2)
    replay.onNext(3)
    replay.subscribe{ Log.d(TAG,"$it") } // - подписка после эмитов -> на replay доступны все эмиты

}