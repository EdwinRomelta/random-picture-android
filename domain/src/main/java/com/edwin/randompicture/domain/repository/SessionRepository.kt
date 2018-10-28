package com.edwin.randompicture.domain.repository

import com.edwin.randompicture.domain.interactor.usecase.Login
import io.reactivex.Completable

interface SessionRepository {

    fun login(loginParam: Login.LoginParam): Completable
}