package com.edwin.randompicture.cache

import com.edwin.randompicture.cache.db.RandomPictureDatabase
import com.edwin.randompicture.cache.mapper.PendingPostEntityMapper
import com.edwin.randompicture.data.model.PendingPostEntity
import com.edwin.randompicture.data.repository.pendingpost.PendingPostCache
import io.reactivex.Completable
import javax.inject.Inject

class PendingPostCacheImpl @Inject constructor(
        randomPictureDatabase: RandomPictureDatabase,
        private val pendingPostEntityMapper: PendingPostEntityMapper) : PendingPostCache {

    private val cachedPendingPostDao = randomPictureDatabase.cachedPendingPostDao()

    override fun savePendingPost(pendingPostEntity: PendingPostEntity): Completable {
        return Completable.fromCallable {
            cachedPendingPostDao.insertPendingPost(pendingPostEntityMapper.mapToCached(pendingPostEntity))
        }
    }

}