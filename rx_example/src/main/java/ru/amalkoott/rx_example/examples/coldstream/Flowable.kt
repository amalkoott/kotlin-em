package ru.amalkoott.rx_example.examples.coldstream

import android.util.Log
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import ru.amalkoott.rx_example.TAG


val flowable = Flowable.just(1,2,3)
val disposeFlowable = flowable.subscribe{
    Log.e(TAG,"$it")
}
// имитация операции с последовательной выдачей данных
fun dataSourceFlowable(strategy: BackpressureStrategy = BackpressureStrategy.DROP) : Flowable<Int> {
    return Flowable.create({ subscriber ->
        for (i in 0..1_000_000){
            //Thread.sleep(100) // какие-то вычисления
            subscriber.onNext(i) // отправляем subscriber изменения
        }
        subscriber.onComplete()
    }, strategy)
}