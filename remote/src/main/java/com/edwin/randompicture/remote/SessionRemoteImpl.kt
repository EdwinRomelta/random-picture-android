package com.edwin.randompicture.remote

import com.edwin.randompicture.data.model.UserEntity
import com.edwin.randompicture.data.repository.session.SessionRemote
import com.edwin.randompicture.remote.mapper.UserMapper
import com.edwin.randompicture.remote.thirdparty.okhttp.toRequestBody
import io.reactivex.Single
import javax.inject.Inject

class SessionRemoteImpl @Inject constructor(
        private val randomPictureService: RandomPictureService,
        private val userMapper: UserMapper) : SessionRemote {

    override fun doLogin(email: String, password: String): Single<UserEntity> =
            randomPictureService.login(email.toRequestBody(),
                    password.toRequestBody())
                    .map { userMapper.mapFromRemote(it) }
}