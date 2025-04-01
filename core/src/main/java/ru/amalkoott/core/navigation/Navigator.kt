package ru.amalkoott.core.navigation

import androidx.fragment.app.Fragment

interface Navigator {
    fun navigateTo(
        fragment: Fragment,
        addToBackStack: Boolean = true,
        tag: String? = null
    )
    fun goBack()
}

