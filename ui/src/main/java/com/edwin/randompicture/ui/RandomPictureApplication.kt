package com.edwin.randompicture.ui

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import androidx.work.Worker
import com.edwin.randompicture.presentation.thirdparty.dagger.HasWorkerInjector
import com.edwin.randompicture.ui.di.AppInjector
import com.google.firebase.FirebaseApp
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class RandomPictureApplication : Application(), HasActivityInjector, HasWorkerInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var workerDispatchingAndroidInjector: DispatchingAndroidInjector<Worker>

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)

        AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector

    override fun workerInjector() = workerDispatchingAndroidInjector
}