package com.edwin.randompicture.ui.di.module

import android.app.Application
import com.edwin.randompicture.domain.executor.PostExecutionThread
import com.edwin.randompicture.ui.RandomPictureApplication
import com.edwin.randompicture.ui.di.scope.PerActivity
import com.edwin.randompicture.ui.di.scope.PerApplication
import com.edwin.randompicture.ui.screen.UiThread
import com.edwin.randompicture.ui.screen.capture.CaptureActivity
import com.edwin.randompicture.ui.screen.main.MainActivity
import com.edwin.randompicture.ui.screen.signup.SignUpActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [ActivityBindingModule::class])
class UiModule {

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread = uiThread

    @Provides
    @PerApplication
    internal fun provideApplication(randomPictureApplication: RandomPictureApplication): Application = randomPictureApplication
}

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [SignUpActivityModule::class])
    abstract fun contributeSignUpActivity(): SignUpActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [CaptureActivityModule::class])
    abstract fun contributeCaptureActivity(): CaptureActivity
}