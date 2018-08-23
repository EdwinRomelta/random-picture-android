package com.edwin.randompicture.ui.di.module

import android.support.v4.app.Fragment
import com.edwin.randompicture.ui.di.scope.PerFragment
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides

@Module
class BaseFragmentModule {

    @PerFragment
    @Provides
    fun provideRxPermission(fragment: Fragment) = RxPermissions(fragment)
}