package ru.amalkoott.core.worker

import NotificationUtils
import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class ChargeNotifyWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        NotificationUtils.showNotification(
            applicationContext,
            1,
            "Battery status connection",
            "is plugged"
        )
        return Result.success()
    }
}