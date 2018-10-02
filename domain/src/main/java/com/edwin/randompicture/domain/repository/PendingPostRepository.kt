package com.edwin.randompicture.domain.repository

import com.edwin.randompicture.domain.model.PendingPost
import io.reactivex.Flowable
import io.reactivex.Single

interface PendingPostRepository {

    fun savePendingPost(pendingPost: PendingPost): Single<Long>

    fun getPendingPostById(id: Long): Flowable<PendingPost>
}