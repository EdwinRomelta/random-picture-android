package com.edwin.randompicture.data.repository.pendingpost

import android.arch.paging.DataSource
import com.edwin.randompicture.data.model.PendingPostEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface PendingPostCache {

    fun savePendingPost(pendingPostEntity: PendingPostEntity): Single<Long>

    fun getPendingPostDataSource(): Single<DataSource.Factory<Int, PendingPostEntity>>

    fun getPendingPostById(id: Long): Flowable<PendingPostEntity>

    fun deletePendingPost(pendingPostEntity: PendingPostEntity): Completable
}