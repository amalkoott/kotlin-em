package ru.amalkoott.android_example.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.amalkoott.android_example.databinding.FragmentThirdBinding
import ru.amalkoott.core.navigation.NavNodeFragment

class ThirdFragment : NavNodeFragment() {
    private lateinit var binding : FragmentThirdBinding

    override fun setNavigation() {
        with(binding){
            toNextBtn.setOnClickListener {
                navigator?.navigateTo(AndroidExampleFragment())
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
        binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }
}