package com.edwin.randompicture.ui.di.module

import androidx.work.Worker
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.Multibinds

@Module
abstract class AndroidWorkerInjectionModule {

    @Multibinds
    internal abstract fun workerInjectorFactories(): Map<Class<out Worker>, AndroidInjector.Factory<out Worker>>

    @Multibinds
    internal abstract fun workerInjectorFactoriesWithStringKeys(): Map<String, AndroidInjector.Factory<out Worker>>

}