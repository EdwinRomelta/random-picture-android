package com.edwin.randompicture.ui.di.module

import com.edwin.randompicture.data.repository.post.PostRemote
import com.edwin.randompicture.remote.PostRemoteImpl
import com.edwin.randompicture.remote.RandomPictureServiceFactory
import dagger.Module
import dagger.Provides

@Module
class RemoteModule {

    companion object {
        private const val BASE_URL = "https://us-central1-random-picture.cloudfunctions.net/api/"
    }

    @Provides
    fun provideRandomPictureService() = RandomPictureServiceFactory.createService(BASE_URL)

    @Provides
    fun providePostRemote(postRemoteImpl: PostRemoteImpl): PostRemote = postRemoteImpl
}