package com.edwin.randompicture.ui.di.module

import android.arch.lifecycle.ViewModelProvider
import com.edwin.randompicture.ui.di.scope.PerApplication
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    @PerApplication
    internal fun provideViewModelFactory(): ViewModelProvider.Factory = ViewModelProvider.NewInstanceFactory()
}