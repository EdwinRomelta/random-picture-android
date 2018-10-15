package com.edwin.randompicture.cache

import com.edwin.randompicture.cache.db.RandomPictureDatabase
import com.edwin.randompicture.cache.mapper.PendingPostEntityMapper
import com.edwin.randompicture.data.model.PendingPostEntity
import com.edwin.randompicture.data.repository.pendingpost.PendingPostCache
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class PendingPostCacheImpl @Inject constructor(
        randomPictureDatabase: RandomPictureDatabase,
        private val pendingPostEntityMapper: PendingPostEntityMapper) : PendingPostCache {

    private val cachedPendingPostDao = randomPictureDatabase.cachedPendingPostDao()

    override fun savePendingPost(pendingPostEntity: PendingPostEntity): Single<Long> {
        return Single.fromCallable {
            cachedPendingPostDao.insertPendingPost(pendingPostEntityMapper.mapToCached(pendingPostEntity))
        }
    }

    override fun getPendingPostById(id: Long): Flowable<PendingPostEntity> =
            cachedPendingPostDao.getPendingPostById(id)
                    .map { pendingPostEntityMapper.mapFromCached(it) }
                    .distinctUntilChanged()
}