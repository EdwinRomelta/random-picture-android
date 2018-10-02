package com.edwin.randompicture.ui.di.module.worker

import com.edwin.randompicture.presentation.work.UploadWorker
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface UploadWorkerModule : AndroidInjector<UploadWorker> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<UploadWorker>()
}