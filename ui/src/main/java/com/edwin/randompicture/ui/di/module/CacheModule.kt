package com.edwin.randompicture.ui.di.module

import android.app.Application
import com.edwin.randompicture.cache.PendingPostCacheImpl
import com.edwin.randompicture.cache.PhotoCacheImpl
import com.edwin.randompicture.cache.PostCacheImpl
import com.edwin.randompicture.cache.SessionCacheImpl
import com.edwin.randompicture.cache.db.RandomPictureDatabase
import com.edwin.randompicture.data.repository.pendingpost.PendingPostCache
import com.edwin.randompicture.data.repository.photo.PhotoCache
import com.edwin.randompicture.data.repository.post.PostCache
import com.edwin.randompicture.data.repository.session.SessionCache
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
    fun providePostCache(postCacheImpl: PostCacheImpl): PostCache = postCacheImpl

    @PerApplication
    @Provides
    fun providePendingPostCache(pendingPostCacheImpl: PendingPostCacheImpl): PendingPostCache = pendingPostCacheImpl

    @PerApplication
    @Provides
    fun provideSessionCache(sessionCacheImpl: SessionCacheImpl): SessionCache = sessionCacheImpl
}