package com.edwin.randompicture.di.module

import com.edwin.randompicture.data.executor.JobExecutor
import com.edwin.randompicture.di.scope.PerApplication
import com.edwin.randompicture.domain.executor.ThreadExecutor
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }
}