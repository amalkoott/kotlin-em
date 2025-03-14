package ru.amalkoott.rx_example.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import ru.amalkoott.rx_example.databinding.FragmentTimerBinding
import java.util.concurrent.TimeUnit


class TimerFragment : Fragment() {

    private lateinit var binding: FragmentTimerBinding
    private lateinit var timer: Disposable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTimerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.timerButton.setOnClickListener { timer = Observable.interval(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                binding.textView.text = it.toString()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer.dispose()
    }
}
