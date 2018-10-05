package com.edwin.randompicture.data.source.post

import javax.inject.Inject

class PostDataStoreFactory @Inject constructor(
        private val postCacheDataStore: PostCacheDataStore,
        private val postRemoteDataStore: PostRemoteDataStore
) {
    fun retrieveCacheDataStore() = postCacheDataStore

    fun retrieveRemoteDataStore() = postRemoteDataStore
}