package com.edwin.randompicture.data.source.pendingpost

import com.edwin.randompicture.data.model.PendingPostEntity
import com.edwin.randompicture.data.repository.pendingpost.PendingPostCache
import com.edwin.randompicture.data.repository.pendingpost.PendingPostDataStore
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class PendingPostCacheDataStore @Inject constructor(
        private val pendingPostCache: PendingPostCache) : PendingPostDataStore {

    override fun savePendingPost(pendingPostEntity: PendingPostEntity): Single<Long> =
            pendingPostCache.savePendingPost(pendingPostEntity)

    override fun getPendingPostById(id: Long): Flowable<PendingPostEntity> =
            pendingPostCache.getPendingPostById(id)
}