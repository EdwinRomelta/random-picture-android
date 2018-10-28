package com.edwin.randompicture.data.repository.session

import com.edwin.randompicture.data.model.SessionEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface SessionCache {

    fun store(sessionEntity: SessionEntity): Completable

    fun get(): Flowable<SessionEntity>

    fun clear(): Completable
}