package ru.amalkoott.rx_example.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import ru.amalkoott.rx_example.TAG
import ru.amalkoott.rx_example.databinding.FragmentEditTextBinding
import java.util.concurrent.TimeUnit

class EditTextFragment : Fragment() {

    private lateinit var binding : FragmentEditTextBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditTextBinding.inflate(inflater, container, false)
        return binding.root
    }

    private lateinit var inputSubscription : Disposable

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputSubscription = getInputObservable()
            .debounce(3, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                Log.d(TAG,"$it")
            }
    }
    private fun getInputObservable() : Observable<Char> {
        return Observable.create{ subscriber ->
            val listener = object : TextWatcher {
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    s?.let { sequence ->
                        if (count > 0) {
                            val newSymbol = sequence[start + count - 1]
                            subscriber.onNext(newSymbol)
                        }
                    }
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
                override fun afterTextChanged(s: Editable?) { }
            }

            binding.editText.addTextChangedListener(listener)

            subscriber.setCancellable { binding.editText.removeTextChangedListener(listener) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        inputSubscription.dispose()
    }
}