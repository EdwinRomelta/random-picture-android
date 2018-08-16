package com.edwin.randompicture.di.module

import com.edwin.randompicture.di.scope.PerActivity
import com.edwin.randompicture.di.scope.PerFragment
import com.edwin.randompicture.ui.capture.CaptureActivity
import com.edwin.randompicture.ui.capture.fragment.capture.CaptureFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CaptureActivityModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [CaptureFragmentBuildersModule::class])
    abstract fun contributeCaptureActivity(): CaptureActivity
}

@Module
abstract class CaptureFragmentBuildersModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract fun contributeCaptureFragment(): CaptureFragment
}