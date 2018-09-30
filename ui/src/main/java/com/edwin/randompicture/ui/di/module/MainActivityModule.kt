package com.edwin.randompicture.ui.di.module

import android.support.v4.app.Fragment
import com.edwin.randompicture.domain.interactor.usecase.GetPost
import com.edwin.randompicture.presentation.mapper.PostMapper
import com.edwin.randompicture.presentation.viewmodel.post.PostViewModelFactory
import com.edwin.randompicture.ui.di.scope.PerFragment
import com.edwin.randompicture.ui.screen.main.fragment.MainFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [(MainFragmentBuildersModule::class)])
class MainActivityModule {

    fun providePostViewModelFactory(getPost: GetPost, postMapper: PostMapper) =
            PostViewModelFactory(getPost, postMapper)
}

@Module
abstract class MainFragmentBuildersModule {

    @PerFragment
    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    abstract fun contributeMainFragment(): MainFragment
}


@Module(includes = [BaseFragmentModule::class])
class MainFragmentModule {

    @PerFragment
    @Provides
    fun provideFragment(mainFragment: MainFragment): Fragment = mainFragment
}