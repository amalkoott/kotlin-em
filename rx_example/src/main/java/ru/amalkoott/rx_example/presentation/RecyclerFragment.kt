package ru.amalkoott.rx_example.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject
import ru.amalkoott.rx_example.databinding.FragmentRecyclerBinding
import ru.amalkoott.rx_example.utils.getData

class RecyclerFragment : Fragment() {

    private lateinit var binding: FragmentRecyclerBinding
    private lateinit var adapter : SimpleStringsAdapter

    private val positionSubject = PublishSubject.create<Int>()
    private val subscriptions = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerBinding.inflate(inflater, container, false)

        setRecycler()
        setButtonListener()
        observeClickedItemPosition()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        subscriptions.clear()
    }

    // настройка recycler
    private fun setRecycler(){
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = SimpleStringsAdapter(){
            positionSubject.onNext(it)
        }
        binding.recycler.adapter = adapter

    }

    // загружаем данные для отображения в recycler
    private fun setButtonListener(){
        binding.fillRecyclerBtn.setOnClickListener {
            subscriptions.add(
                getData()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { result -> adapter.submitList(result) },
                        { adapter.submitList(listOf("error")) }
                    )
            )
        }
    }

    // подписываемся на positionSubject, чтобы показывать номер элемента в списке
    private fun observeClickedItemPosition(){
        subscriptions.add(
            positionSubject
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    Toast.makeText(requireContext(),"$it",Toast.LENGTH_SHORT).show()
                }
        )
    }
}