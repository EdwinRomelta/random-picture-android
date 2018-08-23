package com.edwin.randompicture.ui.di.module

import com.edwin.randompicture.data.executor.JobExecutor
import com.edwin.randompicture.domain.executor.ThreadExecutor
import com.edwin.randompicture.ui.di.scope.PerApplication
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor = jobExecutor
}