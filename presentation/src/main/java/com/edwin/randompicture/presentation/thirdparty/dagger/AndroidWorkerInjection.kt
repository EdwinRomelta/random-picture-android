package com.edwin.randompicture.presentation.thirdparty.dagger

import androidx.work.Worker
import dagger.android.AndroidInjector


object AndroidWorkerInjection {

    fun inject(worker: Worker) {
        val application = worker.applicationContext
        if (application !is HasWorkerInjector) {
            throw RuntimeException(
                    String.format(
                            "%s does not implement %s",
                            application.javaClass.canonicalName,
                            HasWorkerInjector::class.java.canonicalName))
        }
        val workerInjector = (application as HasWorkerInjector).workerInjector()
        workerInjector.inject(worker)
    }
}

interface HasWorkerInjector {
    fun workerInjector(): AndroidInjector<Worker>
}
