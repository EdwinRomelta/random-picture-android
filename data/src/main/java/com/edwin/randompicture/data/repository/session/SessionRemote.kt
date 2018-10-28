package com.edwin.randompicture.data.repository.session

import com.edwin.randompicture.data.model.SessionEntity
import io.reactivex.Completable
import io.reactivex.Single

interface SessionRemote {

    fun setSession(token: String?): Completable

    fun doRegister(email: String, name: String, password: String): Single<SessionEntity>

    fun doLogin(email: String, password: String): Single<SessionEntity>

}