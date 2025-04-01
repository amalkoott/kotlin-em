package ru.amalkoott.kotlinem

import NotificationUtils
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.amalkoott.android_example.presentation.AndroidExampleFragment
import ru.amalkoott.core.worker.setChargeNotify
import ru.amalkoott.core.navigation.ActivityCloser
import ru.amalkoott.core.navigation.Navigator
import ru.amalkoott.core.navigation.Router


class MainActivity : AppCompatActivity(), Navigator, ActivityCloser {
    private val router = Router(supportFragmentManager, R.id.fragment_container, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NotificationUtils.createNotificationChannel(applicationContext)
        navigateTo(AndroidExampleFragment(), false)

        setChargeNotify(applicationContext)
    }

    override fun close() {
        finish()
    }

    override fun navigateTo(
        fragment: Fragment,
        addToBackStack: Boolean,
        tag: String?
    ) {
        router.navigateTo(fragment,addToBackStack,tag)
    }

    override fun goBack() {
        router.goBack()
    }
}

