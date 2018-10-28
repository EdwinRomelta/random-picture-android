package com.edwin.randompicture.data.source.session

import com.edwin.randompicture.data.model.SessionEntity
import com.edwin.randompicture.data.repository.session.SessionDataStore
import com.edwin.randompicture.data.repository.session.SessionRemote
import com.edwin.randompicture.domain.interactor.usecase.Login
import com.edwin.randompicture.domain.interactor.usecase.Register
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class SessionRemoteDataStore @Inject constructor(
        private val sessionRemote: SessionRemote) : SessionDataStore {

    override fun getSession(): Flowable<SessionEntity> {
        throw UnsupportedOperationException()
    }

    override fun doRegister(registerParam: Register.RegisterParam): Single<SessionEntity> =
            sessionRemote.doRegister(registerParam.email, registerParam.name, registerParam.password)

    override fun doLogin(loginParam: Login.LoginParam): Single<SessionEntity> =
            sessionRemote.doLogin(loginParam.email, loginParam.password)

    override fun store(sessionEntity: SessionEntity): Completable =
            sessionRemote.setSession(sessionEntity.token)

    override fun clear(): Completable =
            sessionRemote.setSession(null)

}