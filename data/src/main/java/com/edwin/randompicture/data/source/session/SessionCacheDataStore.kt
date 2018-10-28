package com.edwin.randompicture.data.source.session

import com.edwin.randompicture.data.model.SessionEntity
import com.edwin.randompicture.data.repository.session.SessionCache
import com.edwin.randompicture.data.repository.session.SessionDataStore
import com.edwin.randompicture.domain.interactor.usecase.Login
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class SessionCacheDataStore @Inject constructor(
        private val sessionCache: SessionCache) : SessionDataStore {

    override fun getSession(): Flowable<SessionEntity> =
            sessionCache.get()

    override fun store(sessionEntity: SessionEntity): Completable =
            sessionCache.store(sessionEntity)

    override fun doLogin(loginParam: Login.LoginParam): Single<SessionEntity> {
        throw UnsupportedOperationException()
    }

    override fun clear(): Completable = sessionCache.clear()

}