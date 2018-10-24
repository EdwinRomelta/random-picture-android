package com.edwin.randompicture.ui.di.module

import androidx.fragment.app.Fragment
import com.edwin.randompicture.ui.binding.FragmentDataBindingComponent
import com.edwin.randompicture.ui.di.scope.PerFragment
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides

@Module
class BaseFragmentModule {

    @PerFragment
    @Provides
    fun provideRxPermission(fragment: Fragment) = RxPermissions(fragment)

    @PerFragment
    @Provides
    fun provideFragmentDataBindingComponent(fragment: Fragment) = FragmentDataBindingComponent(fragment)
}