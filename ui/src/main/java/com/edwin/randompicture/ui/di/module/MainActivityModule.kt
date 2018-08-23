package com.edwin.randompicture.ui.di.module

import com.edwin.randompicture.ui.di.scope.PerFragment
import com.edwin.randompicture.ui.screen.main.fragment.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [(MainFragmentBuildersModule::class)])
class MainActivityModule

@Module
abstract class MainFragmentBuildersModule {

    @PerFragment
    @ContributesAndroidInjector(modules = [BaseFragmentModule::class])
    abstract fun contributeMainFragment(): MainFragment
}