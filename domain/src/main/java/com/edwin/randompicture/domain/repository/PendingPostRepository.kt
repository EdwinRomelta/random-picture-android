package com.edwin.randompicture.domain.repository

import com.edwin.randompicture.domain.model.PendingPost
import io.reactivex.Completable

interface PendingPostRepository {

    fun savePendingPost(pendingPost: PendingPost): Completable
}