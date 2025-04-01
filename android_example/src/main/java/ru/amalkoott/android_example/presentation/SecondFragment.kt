package ru.amalkoott.android_example.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.amalkoott.android_example.databinding.FragmentSecondBinding
import ru.amalkoott.core.navigation.NavNodeFragment

class SecondFragment : NavNodeFragment() {
    private lateinit var binding : FragmentSecondBinding

    override fun setNavigation() {
        with(binding){
            toNextBtn.setOnClickListener {
                navigator?.navigateTo(ThirdFragment())
            }
            toPreBtn.setOnClickListener {
                navigator?.goBack()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }
}