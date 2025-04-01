package ru.amalkoott.core.navigation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class NavNodeFragment : Fragment() {
    var navigator: Navigator? = null

    abstract fun setNavigation()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigator = context as? Navigator
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigation()
    }

    override fun onDetach() {
        super.onDetach()
        navigator = null
    }
}