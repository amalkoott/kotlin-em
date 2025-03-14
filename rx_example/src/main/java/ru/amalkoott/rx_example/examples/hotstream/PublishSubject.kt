package ru.amalkoott.rx_example.examples.hotstream

import android.util.Log
import io.reactivex.rxjava3.subjects.PublishSubject
import ru.amalkoott.rx_example.TAG

// как переписать, чтобы было выведено все?
// 1 способ : подписка до эмитов
// 2 способ : subject с буферизацией? -> reply
fun publish() {
    Log.d(TAG,"Publish example 1:")
    val publish = PublishSubject.create<Int>()
    publish.subscribe{ Log.d(TAG,"$it") }
    publish.onNext(1)
    publish.onNext(2)
    publish.onNext(3)
    // publish.subscribe{ Log.d(TAG,"$it") } // - подписка после эмитов -> на publish доступны эмиты после подписки

}