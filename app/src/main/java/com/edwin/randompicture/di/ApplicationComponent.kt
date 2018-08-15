package com.edwin.randompicture.di

import com.edwin.randompicture.RandomPictureApplication
import com.edwin.randompicture.di.module.MainActivityModule
import com.edwin.randompicture.di.module.ViewModelModule
import com.edwin.randompicture.di.scope.PerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            MainActivityModule::class,
            ViewModelModule::class]
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