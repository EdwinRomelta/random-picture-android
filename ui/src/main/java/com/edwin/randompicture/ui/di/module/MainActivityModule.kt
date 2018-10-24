package com.edwin.randompicture.ui.di.module

import androidx.fragment.app.Fragment
import com.edwin.randompicture.domain.interactor.usecase.GetAndSavePost
import com.edwin.randompicture.domain.interactor.usecase.GetPostDataSource
import com.edwin.randompicture.presentation.mapper.PostMapper
import com.edwin.randompicture.presentation.viewmodel.post.PostViewModelFactory
import com.edwin.randompicture.ui.di.scope.PerFragment
import com.edwin.randompicture.ui.screen.main.fragment.MainFragment
import com.edwin.randompicture.ui.viewholder.PostViewHolder
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [(MainFragmentBuildersModule::class)])
class MainActivityModule {

    fun providePostViewModelFactory(getAndSavePost: GetAndSavePost, getPostDataSource: GetPostDataSource, postMapper: PostMapper) =
            PostViewModelFactory(getAndSavePost, getPostDataSource, postMapper)
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

    @PerFragment
    @Provides
    fun providePostItemDelegate(mainFragment: MainFragment): PostViewHolder.PostItemDelegate = mainFragment
}