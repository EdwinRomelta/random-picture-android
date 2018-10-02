package com.edwin.randompicture.data.repository.pendingpost

import com.edwin.randompicture.data.model.PendingPostEntity
import io.reactivex.Flowable
import io.reactivex.Single

interface PendingPostCache {

    fun savePendingPost(pendingPostEntity: PendingPostEntity): Single<Long>

    fun getPendingPostById(id: Long): Flowable<PendingPostEntity>
}