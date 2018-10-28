package com.edwin.randompicture.data

import com.edwin.randompicture.data.mapper.SessionMapper
import com.edwin.randompicture.data.source.session.SessionDataStoreFactory
import com.edwin.randompicture.domain.interactor.usecase.Login
import com.edwin.randompicture.domain.model.Session
import com.edwin.randompicture.domain.repository.SessionRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class SessionDataRepository @Inject constructor(private val factory: SessionDataStoreFactory,
                                                private val sessionMapper: SessionMapper) : SessionRepository {

    override fun getSession(): Flowable<Session> =
            factory.retrieveCacheDataStore().getSession()
                    .flatMap {
                        factory.retrieveRemoteDataStore().store(it)
                                .toSingle { it }
                                .toFlowable()
                    }
                    .map { sessionMapper.mapFromEntity(it) }

    override fun login(loginParam: Login.LoginParam): Completable =
            factory.retrieveRemoteDataStore().doLogin(loginParam)
                    .flatMapCompletable {
                        factory.retrieveCacheDataStore().store(it)
                    }

    override fun logout(): Completable =
            factory.retrieveCacheDataStore().clear()
                    .andThen(factory.retrieveRemoteDataStore().clear())

}