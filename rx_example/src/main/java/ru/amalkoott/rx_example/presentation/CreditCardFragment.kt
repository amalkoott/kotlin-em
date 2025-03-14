package ru.amalkoott.rx_example.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.amalkoott.rx_example.TAG
import ru.amalkoott.rx_example.databinding.FragmentCreditCardBinding
import ru.amalkoott.rx_example.utils.fromFirstServer
import ru.amalkoott.rx_example.utils.fromSecondServer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.amalkoott.rx_example.utils.RequestResult
import ru.amalkoott.rx_example.utils.fromErrorServer

class CreditCardFragment : Fragment() {

    private lateinit var binding: FragmentCreditCardBinding
    private lateinit var adapter : SimpleStringsAdapter

    private val subscriptions = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreditCardBinding.inflate(inflater, container, false)

        setRecycler()
        setButtonListener()

        return binding.root
    }

    private fun setRecycler(){
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = SimpleStringsAdapter{ }
        binding.recycler.adapter = adapter
    }

    private fun setButtonListener(){
        binding.correctCardBtn.setOnClickListener {
            subscriptions.add(
                loadData()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { cards ->
                            adapter.submitList(cards)
                        },
                        { error ->
                            Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
                        }
                    )
            )
        }
        binding.errorCardBtn.setOnClickListener {
            subscriptions.add(
                loadFallibleData()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { cards ->
                            adapter.submitList(cards)
                        },
                        { error ->
                            Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
                        }
                    )
            )
        }
    }

    private fun loadData() : Single<RequestResult>{
        val first = fromFirstServer().onErrorReturn { emptyList() }
        val second = fromSecondServer().onErrorReturn { emptyList() }

        return Single.zip(first,second) { cards1, cards2 ->
            cards1 + cards2
        }
    }
    private fun loadFallibleData() : Single<RequestResult>{
        val first = fromFirstServer()
        val error = fromErrorServer()

        return Single.zip(first,error) { cards1, cards2 ->
            cards1 + cards2
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        subscriptions.clear()
    }
}