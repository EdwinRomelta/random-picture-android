package com.edwin.randompicture.data

import com.edwin.randompicture.data.mapper.PendingPostMapper
import com.edwin.randompicture.data.source.pendingpost.PendingPostDataStoreFactory
import com.edwin.randompicture.domain.model.PendingPost
import com.edwin.randompicture.domain.repository.PendingPostRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class PendingPostDataRepository @Inject constructor(
        private val factory: PendingPostDataStoreFactory,
        private val pendingPostMapper: PendingPostMapper
) : PendingPostRepository {

    override fun savePendingPost(pendingPost: PendingPost): Single<Long> {
        return factory.retrieveCacheDataStore()
                .savePendingPost(pendingPostMapper.mapToEntity(pendingPost))
    }

    override fun getPendingPostById(id: Long): Flowable<PendingPost> {
        return factory.retrieveCacheDataStore().getPendingPostById(id)
                .map { pendingPostMapper.mapFromEntity(it) }
    }
}