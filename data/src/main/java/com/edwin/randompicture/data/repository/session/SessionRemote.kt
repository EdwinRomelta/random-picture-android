package com.edwin.randompicture.data.repository.session

import com.edwin.randompicture.data.model.SessionEntity
import io.reactivex.Completable
import io.reactivex.Single

interface SessionRemote {

    fun addSession(token: String?): Completable

    fun doLogin(email: String, password: String): Single<SessionEntity>
}