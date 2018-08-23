package com.edwin.randompicture.data.source.pendingpost

import com.edwin.randompicture.data.model.PendingPostEntity
import com.edwin.randompicture.data.repository.pendingpost.PendingPostCache
import com.edwin.randompicture.data.repository.pendingpost.PendingPostDataStore
import io.reactivex.Completable
import javax.inject.Inject

class PendingPostCacheDataStore @Inject constructor(
        private val pendingPostCache: PendingPostCache) : PendingPostDataStore {

    override fun savePendingPost(pendingPostEntity: PendingPostEntity): Completable =
            pendingPostCache.savePendingPost(pendingPostEntity)

}