package com.edwin.randompicture.data.source.session

import com.edwin.randompicture.data.model.UserEntity
import com.edwin.randompicture.data.repository.session.SessionCache
import com.edwin.randompicture.data.repository.session.SessionDataStore
import com.edwin.randompicture.domain.interactor.usecase.Login
import io.reactivex.Single
import javax.inject.Inject

class SessionCacheDataStore @Inject constructor(
        private val sessionCache: SessionCache) : SessionDataStore {

    override fun doLogin(loginParam: Login.LoginParam): Single<UserEntity> {
        throw UnsupportedOperationException()
    }

}