package com.edwin.randompicture.di.module

import android.arch.lifecycle.ViewModelProvider
import com.edwin.randompicture.di.scope.PerApplication
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    @PerApplication
    internal fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelProvider.NewInstanceFactory()
    }
}