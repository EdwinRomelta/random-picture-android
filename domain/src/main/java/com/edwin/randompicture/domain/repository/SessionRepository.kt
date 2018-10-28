package com.edwin.randompicture.domain.repository

import com.edwin.randompicture.domain.interactor.usecase.Login
import com.edwin.randompicture.domain.model.Session
import io.reactivex.Completable
import io.reactivex.Flowable

interface SessionRepository {

    fun getSession(): Flowable<Session>

    fun login(loginParam: Login.LoginParam): Completable
}