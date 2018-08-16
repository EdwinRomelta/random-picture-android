package com.edwin.randompicture.di.module

import com.edwin.randompicture.di.scope.PerActivity
import com.edwin.randompicture.di.scope.PerFragment
import com.edwin.randompicture.ui.main.MainActivity
import com.edwin.randompicture.ui.main.fragment.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainFragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}

@Module
abstract class MainFragmentBuildersModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment
}