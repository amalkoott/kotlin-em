package ru.amalkoott.android_example.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
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

        Log.d("VIEW_TAG","created")

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("VIEW_TAG","destroyed")
    }
}