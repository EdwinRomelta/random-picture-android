package com.edwin.randompicture.data.repository.session

import com.edwin.randompicture.data.model.UserEntity
import com.edwin.randompicture.domain.interactor.usecase.Login
import io.reactivex.Single

interface SessionDataStore {

    fun doLogin(loginParam: Login.LoginParam): Single<UserEntity>
}