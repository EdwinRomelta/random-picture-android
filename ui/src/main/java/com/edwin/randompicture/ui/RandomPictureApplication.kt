package com.edwin.randompicture.ui

import android.app.Activity
import android.app.Application
import androidx.work.Worker
import com.edwin.randompicture.presentation.thirdparty.dagger.HasWorkerInjector
import com.edwin.randompicture.ui.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class RandomPictureApplication : Application(), HasActivityInjector, HasWorkerInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var workerDispatchingAndroidInjector: DispatchingAndroidInjector<Worker>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector

    override fun workerInjector() = workerDispatchingAndroidInjector
}