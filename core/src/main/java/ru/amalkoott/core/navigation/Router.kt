package ru.amalkoott.core.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit

interface ActivityCloser { fun close() }

class Router(
    private val fragmentManager: FragmentManager,
    private val containerId: Int,
    private val closer: ActivityCloser? = null
) {
    fun navigateTo(
        fragment: Fragment,
        addToBackStack: Boolean = true,
        tag: String? = null
    ) {
        fragmentManager.commit {
            replace(containerId, fragment, tag)
            if (addToBackStack) addToBackStack(tag)
        }
    }

    fun goBack() {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        } else {
            closer?.close()
        }
    }
}