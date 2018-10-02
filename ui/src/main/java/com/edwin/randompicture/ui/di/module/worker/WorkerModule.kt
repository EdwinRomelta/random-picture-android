package com.edwin.randompicture.ui.di.module.worker

import androidx.work.Worker
import com.edwin.randompicture.presentation.work.UploadWorker
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = [UploadWorkerModule::class])
abstract class WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(UploadWorker::class)
    internal abstract fun bindUploadWorkerFactory(uploadWorker: UploadWorkerModule.Builder): AndroidInjector.Factory<out Worker>


}