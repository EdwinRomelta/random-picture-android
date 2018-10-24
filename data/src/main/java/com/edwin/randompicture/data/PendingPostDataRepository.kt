package com.edwin.randompicture.data

import androidx.paging.DataSource
import com.edwin.randompicture.data.mapper.PendingPostMapper
import com.edwin.randompicture.data.source.pendingpost.PendingPostDataStoreFactory
import com.edwin.randompicture.domain.model.PendingPost
import com.edwin.randompicture.domain.repository.PendingPostRepository
import io.reactivex.Completable
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

    override fun getPendingPostDataSource(): Single<DataSource.Factory<Int, PendingPost>> {
        return factory.retrieveCacheDataStore().getPendingPostDataSource()
                .map { datasource ->
                    datasource.map { pendingPostMapper.mapFromEntity(it) }
                }
    }

    override fun getPendingPostById(id: Long): Flowable<PendingPost> {
        return factory.retrieveCacheDataStore().getPendingPostById(id)
                .map { pendingPostMapper.mapFromEntity(it) }
    }

    override fun deletePendingPost(pendingPost: PendingPost): Completable {
        return factory.retrieveCacheDataStore().deletePendingPost(pendingPostMapper.mapToEntity(pendingPost))
    }
}