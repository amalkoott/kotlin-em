package ru.amalkoott.rx_example.examples.coldstream

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.amalkoott.rx_example.TAG
import java.util.concurrent.TimeUnit


// создание
val observable = Observable.just(arrayOf(1,2,3))
val disposeObservable = observable.subscribe{
    Log.e(TAG,"${it.joinToString { "$it " }}")
}

// имитация операции с последовательной выдачей данных
fun dataSourceObservable() : Observable<Int> {
    return Observable.create{ subscriber ->
        for (i in 0..10){
            Thread.sleep(10000) // какие-то вычисления
            subscriber.onNext(i) // отправляем subscriber изменения
        }
        subscriber.onComplete()
    }
}


fun test1() : Disposable{
    return Observable.just(1)
        .map{
            Log.d(TAG, "map_1 thread = ${Thread.currentThread().name}")
        }
        .observeOn(Schedulers.single())

        .map{
            Log.d(TAG, "map_2 thread = ${Thread.currentThread().name}")
        }
        .subscribeOn(Schedulers.io())
        .map{
            Log.d(TAG, "map_3 thread = ${Thread.currentThread().name}")
        }
        .subscribe{
            Log.d(TAG, "subscribe thread = ${Thread.currentThread().name}")
        }

}



fun subscribeObserveOnConflictExample() : Disposable{
    return Observable.timer(10000, TimeUnit.MILLISECONDS,Schedulers.newThread())
        .subscribeOn(Schedulers.io()) // игнорируется из-за дефолтного планировщика
        .map{
            Log.d(TAG, "map thread = ${Thread.currentThread().name}") // (2) new thread
        }
        .doOnSubscribe{
            Log.d(TAG, "onSubscribe thread = ${Thread.currentThread().name}") // (1) computation
        }
        .subscribeOn(Schedulers.computation()) // отработает только для doOnSubscribe
        .observeOn(Schedulers.single())
        .flatMap{
            Log.d(TAG, "flatMap thread = ${Thread.currentThread().name}") // (3) single

            Observable.just(it)
                .subscribeOn(Schedulers.io())

        }
        .subscribe{
            Log.d(TAG, "subscribe thread = ${Thread.currentThread().name}") // (4) io (не игнорируется, т.к. создается новый observable, подписка идет на его объекты, которые выполняются в io )
        }
}