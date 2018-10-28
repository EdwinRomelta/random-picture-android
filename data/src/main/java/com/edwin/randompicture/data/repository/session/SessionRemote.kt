package com.edwin.randompicture.data.repository.session

import com.edwin.randompicture.data.model.UserEntity
import io.reactivex.Single

interface SessionRemote {

    fun doLogin(email: String, password: String): Single<UserEntity>
}