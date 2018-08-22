package com.edwin.randompicture.di.module

import android.support.v4.app.Fragment
import com.edwin.randompicture.di.scope.PerFragment
import com.edwin.randompicture.domain.interactor.usecase.SavePhoto
import com.edwin.randompicture.mapper.PhotoMapper
import com.edwin.randompicture.ui.capture.fragment.CaptureFragment
import com.edwin.randompicture.viewmodel.PhotoViewModelFactory
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
}

@Module(includes = [BaseFragmentModule::class])
class CaptureFragmentModule {

    @PerFragment
    @Provides
    fun provideFragment(captureFragment: CaptureFragment): Fragment = captureFragment
}