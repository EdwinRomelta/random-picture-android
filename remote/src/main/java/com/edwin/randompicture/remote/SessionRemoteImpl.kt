package com.edwin.randompicture.remote

import com.edwin.randompicture.data.model.SessionEntity
import com.edwin.randompicture.data.repository.session.SessionRemote
import com.edwin.randompicture.remote.mapper.UserMapper
import com.edwin.randompicture.remote.thirdparty.okhttp.toRequestBody
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SessionRemoteImpl @Inject constructor(
        private val randomPictureService: RandomPictureService,
        private val userMapper: UserMapper) : SessionRemote {

    override fun setSession(token: String?): Completable =
            Completable.fromCallable {
                if (token != null) {
                    RandomPictureServiceFactory.addSession(token)
                } else {
                    RandomPictureServiceFactory.clearSession()
                }
            }

    override fun doLogin(email: String, password: String): Single<SessionEntity> =
            randomPictureService.login(email.toRequestBody(),
                    password.toRequestBody())
                    .doOnSuccess {
                        if (it.token != null)
                            RandomPictureServiceFactory.addSession(it.token)
                    }
                    .map { userMapper.mapFromRemote(it) }
}