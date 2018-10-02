package com.edwin.randompicture.presentation.work

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.edwin.randompicture.presentation.thirdparty.dagger.AndroidWorkerInjection
import io.reactivex.Single

abstract class BaseObservableWorker(context: Context, params: WorkerParameters)
    : Worker(context, params) {

    final override fun doWork(): Result {
        AndroidWorkerInjection.inject(this)
        return try {
            createWork().blockingGet()
        } catch (e: Exception) {
            Result.FAILURE
        }
    }

    abstract fun createWork(): Single<Result>
}