package com.edwin.randompicture.data.source.session

import com.edwin.randompicture.data.model.UserEntity
import com.edwin.randompicture.data.repository.session.SessionDataStore
import com.edwin.randompicture.data.repository.session.SessionRemote
import com.edwin.randompicture.domain.interactor.usecase.Login
import io.reactivex.Single
import javax.inject.Inject

class SessionRemoteDataStore @Inject constructor(
        private val sessionRemote: SessionRemote) : SessionDataStore {

    override fun doLogin(loginParam: Login.LoginParam): Single<UserEntity> =
            sessionRemote.doLogin(loginParam.email, loginParam.password)

}