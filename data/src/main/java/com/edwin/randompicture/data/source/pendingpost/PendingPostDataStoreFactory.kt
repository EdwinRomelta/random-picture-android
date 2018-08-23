package com.edwin.randompicture.data.source.pendingpost

import javax.inject.Inject

class PendingPostDataStoreFactory @Inject constructor(
        private val pendingPostCacheDataStore: PendingPostCacheDataStore
) {

    fun retrieveCacheDataStore() = pendingPostCacheDataStore
}