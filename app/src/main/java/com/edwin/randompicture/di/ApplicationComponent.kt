package com.edwin.randompicture.di

import com.edwin.randompicture.RandomPictureApplication
import com.edwin.randompicture.di.scope.PerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@PerApplication
@Component(
        modules = [
            AndroidInjectionModule::class]
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