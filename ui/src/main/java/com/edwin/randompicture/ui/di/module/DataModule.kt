package com.edwin.randompicture.ui.di.module

import com.edwin.randompicture.cache.PhotoCacheImpl
import com.edwin.randompicture.data.PhotoDataRepository
import com.edwin.randompicture.data.repository.photo.PhotoCache
import com.edwin.randompicture.domain.repository.PhotoRepository
import com.edwin.randompicture.ui.di.scope.PerApplication
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @PerApplication
    @Provides
    fun providePhotoRepository(photoDataRepository: PhotoDataRepository): PhotoRepository = photoDataRepository

    @PerApplication
    @Provides
    fun providePhotoCache(photoCacheImpl: PhotoCacheImpl): PhotoCache = photoCacheImpl
}