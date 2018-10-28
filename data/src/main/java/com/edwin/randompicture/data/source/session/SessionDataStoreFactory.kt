package com.edwin.randompicture.data.source.session

import javax.inject.Inject

class SessionDataStoreFactory @Inject constructor(
        private val sessionCacheDataStore: SessionCacheDataStore,
        private val sessionRemoteDataStore: SessionRemoteDataStore
) {
    fun retrieveCacheDataStore() = sessionCacheDataStore

    fun retrieveRemoteDataStore() = sessionRemoteDataStore
}