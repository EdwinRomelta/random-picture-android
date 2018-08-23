package com.edwin.randompicture.ui.di

import com.edwin.randompicture.ui.RandomPictureApplication
import com.edwin.randompicture.ui.di.module.*
import com.edwin.randompicture.ui.di.scope.PerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            MainActivityModule::class,
            CaptureActivityModule::class,
            UiModule::class,
            PresentationModule::class,
            DomainModule::class,
            DataModule::class]
)
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: RandomPictureApplication): Builder

        fun build(): ApplicationComponent
    }

    fun inject(randomPictureApplication: RandomPictureApplication)
}