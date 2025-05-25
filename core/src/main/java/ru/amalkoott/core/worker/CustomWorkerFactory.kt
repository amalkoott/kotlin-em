package ru.amalkoott.core.worker

import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import kotlin.random.Random

class CustomWorkerFactory(
    private val someDependency: Any
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        // если return = null, то WM использует дефолтную WorkerFactory, чтобы все равно создать работу
        return if (Random.nextBoolean()) PeriodicCheckServer(
            appContext,
            workerParameters,
            someDependency
        ) else null
    }
}