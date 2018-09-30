package com.edwin.randompicture.data.source.post

import javax.inject.Inject

class PostDataStoreFactory @Inject constructor(
        private val postRemoteDataStore: PostRemoteDataStore
) {

    fun retrieveRemoteDataStore() = postRemoteDataStore
}