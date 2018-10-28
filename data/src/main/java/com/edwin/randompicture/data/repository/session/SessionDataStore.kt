package com.edwin.randompicture.data.repository.session

import com.edwin.randompicture.data.model.SessionEntity
import com.edwin.randompicture.domain.interactor.usecase.Login
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface SessionDataStore {

    fun getSession(): Flowable<SessionEntity>

    fun doLogin(loginParam: Login.LoginParam): Single<SessionEntity>

    fun store(sessionEntity: SessionEntity): Completable

    fun clear(): Completable
}