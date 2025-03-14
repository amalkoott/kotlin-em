package ru.amalkoott.rx_example.examples

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import ru.amalkoott.rx_example.TAG
import ru.amalkoott.rx_example.databinding.FragmentBlankBinding
import ru.amalkoott.rx_example.examples.coldstream.dataSourceCompletable
import ru.amalkoott.rx_example.examples.coldstream.dataSourceFlowable
import ru.amalkoott.rx_example.examples.coldstream.dataSourceMaybe
import ru.amalkoott.rx_example.examples.coldstream.dataSourceObservable
import ru.amalkoott.rx_example.examples.coldstream.dataSourceSingle

class BlankFragment : Fragment() {

    private lateinit var binding: FragmentBlankBinding



    private fun setButtonClickListener() {
        with(binding){
            buttonObservable.setOnClickListener {
                android.util.Log.d(ru.amalkoott.rx_example.TAG,"observable clicked")
                startObservable()
            }
            buttonFlowable.setOnClickListener {
                android.util.Log.d(ru.amalkoott.rx_example.TAG,"observable clicked")
                startFlowable()
            }
            buttonSingle.setOnClickListener {
                android.util.Log.d(ru.amalkoott.rx_example.TAG,"single clicked")
                startSingle()
            }
            buttonCompletable.setOnClickListener {
                android.util.Log.d(ru.amalkoott.rx_example.TAG,"completable clicked")
                startCompletable()
            }
            buttonMaybe.setOnClickListener {
                android.util.Log.d(ru.amalkoott.rx_example.TAG,"maybe clicked")
                startMaybe()
            }
        }
    }

    private fun startObservable(){
        val dispose : Disposable = dataSourceObservable()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                binding.buttonObservable.text = it.toString()
                Log.e(TAG,"value $it at thread : ${Thread.currentThread().name}")
            },{
                Log.e(TAG,"$it")
            },{
                binding.buttonObservable.text = "Observable"
                Log.e(TAG,"complete")
            })
        Log.e(TAG,"created")
    }

    private fun startFlowable(){
        val subscriber = object : Subscriber<Int> {
            override fun onSubscribe(s: Subscription?) {
                Log.d(TAG,"subscriber for flowable created")
            }

            override fun onError(t: Throwable?) {
                Log.d(TAG,"flowable error")
            }

            override fun onComplete() {
                Log.d(TAG,"flowable completed")
            }

            override fun onNext(t: Int?) {
                Log.d(TAG,"$t")
            }
        }
        val flowableDispose = dataSourceFlowable()
            .subscribe(subscriber)
    }

    private fun startMaybe(){
        val disposeMaybe = dataSourceMaybe()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> Toast.makeText(requireContext(),result.joinToString { "$it" }, Toast.LENGTH_SHORT).show() },
                { error -> Toast.makeText(requireContext(),"Maybe Error : $error", Toast.LENGTH_SHORT).show() },
                { Toast.makeText(requireContext(),"Maybe completed", Toast.LENGTH_SHORT).show() }
            )
    }

    private fun startSingle(){
        val dispose = dataSourceSingle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) // т.к. делаем Toast, важно переключить на главный поток
            // в Single мы не можем отследить onComplete (и нам это не нужно), т.к. работа идет для одного объекта
            .subscribe(
                { result -> Toast.makeText(requireContext(),result.joinToString { "$it" }, Toast.LENGTH_SHORT).show() },
                { error -> Toast.makeText(requireContext(),"Single Error : $error", Toast.LENGTH_SHORT).show() }
            )
    }

    private fun startCompletable(){
        val disposeCompletable = dataSourceCompletable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            // в Completable нет доступа к результату
            .subscribe (
                { Toast.makeText(requireContext(),"Completable done", Toast.LENGTH_SHORT).show() },
                { error -> Toast.makeText(requireContext(),"${error.message}", Toast.LENGTH_SHORT).show() }
            )
    }
}