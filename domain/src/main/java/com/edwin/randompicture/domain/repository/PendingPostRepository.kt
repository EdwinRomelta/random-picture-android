package com.edwin.randompicture.domain.repository

import android.arch.paging.DataSource
import com.edwin.randompicture.domain.model.PendingPost
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface PendingPostRepository {

    fun savePendingPost(pendingPost: PendingPost): Single<Long>

    fun getPendingPostDataSource(): Single<DataSource.Factory<Int, PendingPost>>

    fun getPendingPostById(id: Long): Flowable<PendingPost>

    fun deletePendingPost(pendingPost: PendingPost): Completable
}