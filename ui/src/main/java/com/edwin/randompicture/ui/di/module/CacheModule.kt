package com.edwin.randompicture.ui.di.module

import android.app.Application
import com.edwin.randompicture.cache.PendingPostCacheImpl
import com.edwin.randompicture.cache.PhotoCacheImpl
import com.edwin.randompicture.cache.db.RandomPictureDatabase
import com.edwin.randompicture.data.repository.pendingpost.PendingPostCache
import com.edwin.randompicture.data.repository.photo.PhotoCache
import com.edwin.randompicture.ui.di.scope.PerApplication
import dagger.Module
import dagger.Provides

@Module
class CacheModule {

    @Provides
    fun provideRandomPictureDatabase(application: Application) = RandomPictureDatabase.initialize(application)

    @PerApplication
    @Provides
    fun providePhotoCache(photoCacheImpl: PhotoCacheImpl): PhotoCache = photoCacheImpl

    @PerApplication
    @Provides
    fun providePendingPostCache(pendingPostCacheImpl: PendingPostCacheImpl): PendingPostCache = pendingPostCacheImpl
}