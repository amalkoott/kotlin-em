package ru.amalkoott.core.worker

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

fun setChargeNotify(context: Context){
    val constraints = Constraints.Builder()
        .setRequiresCharging(true)
        .build()

    val workRequest = PeriodicWorkRequest.Builder(
        ChargeNotifyWorker::class.java,
        15, TimeUnit.MINUTES
    )
        .setInitialDelay(0, TimeUnit.SECONDS)
        .setConstraints(constraints)
        .build()

    val work = WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "charging_notification_work",
        ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
        workRequest
    )

    Log.d(ChargeNotifyWorker::class.simpleName, "${work.state.value}")
}