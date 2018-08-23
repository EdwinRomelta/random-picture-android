package com.edwin.randompicture.data.repository.pendingpost

import com.edwin.randompicture.data.model.PendingPostEntity
import io.reactivex.Completable

interface PendingPostDataStore {

    fun savePendingPost(pendingPostEntity: PendingPostEntity): Completable
}