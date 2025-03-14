package ru.amalkoott.rx_example

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.amalkoott.rx_example.databinding.FragmentRxExampleBinding
import ru.amalkoott.rx_example.presentation.CreditCardFragment
import ru.amalkoott.rx_example.presentation.EditTextFragment
import ru.amalkoott.rx_example.presentation.RecyclerFragment
import ru.amalkoott.rx_example.presentation.TimerFragment

internal val TAG = RxExampleFragment::class.java.simpleName

class RxExampleFragment : Fragment() {

    private lateinit var binding: FragmentRxExampleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRxExampleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            toRecycler.setOnClickListener { replaceFragment(RecyclerFragment()) }
            toTimer.setOnClickListener { replaceFragment(TimerFragment()) }
            toEditText.setOnClickListener { replaceFragment(EditTextFragment()) }
            toCards.setOnClickListener { replaceFragment(CreditCardFragment()) }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null) // Добавляем транзакцию в back stack
        fragmentTransaction.commit()
    }

}