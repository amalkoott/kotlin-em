package ru.amalkoott.core.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class PeriodicCheckServer(
    private val context: Context,
    private val params: WorkerParameters,
    private val dependency: Any
) : Worker(context, params) {

    override fun doWork(): Result {
        val attemptCount = inputData.getInt("ATTEMPT_COUNT", 0)

        Log.d(PeriodicCheckServer::class.simpleName, "attempt count = $attemptCount")

        return Result.retry()
    }

}