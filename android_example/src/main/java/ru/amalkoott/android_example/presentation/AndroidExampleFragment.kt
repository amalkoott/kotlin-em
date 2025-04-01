package ru.amalkoott.android_example.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.amalkoott.android_example.databinding.FragmentAndroidExampleBinding
import ru.amalkoott.core.navigation.NavNodeFragment

class AndroidExampleFragment : NavNodeFragment() {
    private lateinit var binding : FragmentAndroidExampleBinding

    override fun setNavigation() {
        with(binding){
            toNextBtn.setOnClickListener {
                navigator?.navigateTo(SecondFragment())
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
        binding = FragmentAndroidExampleBinding.inflate(inflater, container, false)
        return binding.root
    }
}