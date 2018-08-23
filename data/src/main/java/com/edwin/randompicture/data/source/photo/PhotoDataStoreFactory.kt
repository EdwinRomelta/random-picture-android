package com.edwin.randompicture.data.source.photo

import javax.inject.Inject

class PhotoDataStoreFactory @Inject constructor(
        private val photoCacheDataStore: PhotoCacheDataStore
) {

    fun retrieveCacheDataStore() = photoCacheDataStore
}