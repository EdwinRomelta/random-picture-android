package com.edwin.randompicture.ui.di.module

import androidx.fragment.app.Fragment
import com.edwin.randompicture.domain.interactor.usecase.GetAndSavePost
import com.edwin.randompicture.domain.interactor.usecase.GetPostDataSource
import com.edwin.randompicture.presentation.mapper.PostMapper
import com.edwin.randompicture.presentation.viewmodel.post.PostViewModelFactory
import com.edwin.randompicture.ui.di.scope.PerFragment
import com.edwin.randompicture.ui.screen.signup.fragment.LoginFragment
import com.edwin.randompicture.ui.screen.signup.fragment.RegisterFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [(LoginFragmentBuildersModule::class)])
class SignUpActivityModule {

    fun providePostViewModelFactory(getAndSavePost: GetAndSavePost, getPostDataSource: GetPostDataSource, postMapper: PostMapper) =
            PostViewModelFactory(getAndSavePost, getPostDataSource, postMapper)
}

@Module
abstract class LoginFragmentBuildersModule {

    @PerFragment
    @ContributesAndroidInjector(modules = [LoginFragmentModule::class])
    abstract fun contributeLoginFragment(): LoginFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [RegisterFragmentModule::class])
    abstract fun contributeRegisterFragment(): RegisterFragment
}

@Module(includes = [BaseFragmentModule::class])
class LoginFragmentModule {

    @PerFragment
    @Provides
    fun provideFragment(loginFragment: LoginFragment): Fragment = loginFragment
}


@Module(includes = [BaseFragmentModule::class])
class RegisterFragmentModule {

    @PerFragment
    @Provides
    fun provideFragment(registerFragment: RegisterFragment): Fragment = registerFragment
}