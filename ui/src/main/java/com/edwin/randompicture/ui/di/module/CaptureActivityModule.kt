package com.edwin.randompicture.ui.di.module

import android.support.v4.app.Fragment
import com.edwin.randompicture.domain.interactor.usecase.SavePhoto
import com.edwin.randompicture.presentation.mapper.PhotoMapper
import com.edwin.randompicture.presentation.viewmodel.PhotoViewModelFactory
import com.edwin.randompicture.ui.di.scope.PerFragment
import com.edwin.randompicture.ui.screen.capture.fragment.CaptureFragment
import com.edwin.randompicture.ui.screen.capture.fragment.PublishFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [(CaptureFragmentBuildersModule::class)])
class CaptureActivityModule {

    @Provides
    fun providePhotoViewModelFactory(savePhoto: SavePhoto,
                                     photoMapper: PhotoMapper):
            PhotoViewModelFactory {
        return PhotoViewModelFactory(savePhoto, photoMapper)
    }
}

@Module
abstract class CaptureFragmentBuildersModule {

    @PerFragment
    @ContributesAndroidInjector(modules = [CaptureFragmentModule::class])
    abstract fun contributeCaptureFragment(): CaptureFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [PublishFragmentModule::class])
    abstract fun contributePublishFragment(): PublishFragment
}

@Module(includes = [BaseFragmentModule::class])
class CaptureFragmentModule {

    @PerFragment
    @Provides
    fun provideFragment(captureFragment: CaptureFragment): Fragment = captureFragment
}

@Module(includes = [BaseFragmentModule::class])
class PublishFragmentModule {

    @PerFragment
    @Provides
    fun provideFragment(publishFragment: PublishFragment): Fragment = publishFragment
}