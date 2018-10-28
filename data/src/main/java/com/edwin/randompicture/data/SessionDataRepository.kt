package com.edwin.randompicture.data

import com.edwin.randompicture.data.mapper.UserMapper
import com.edwin.randompicture.data.source.session.SessionDataStoreFactory
import com.edwin.randompicture.domain.interactor.usecase.Login
import com.edwin.randompicture.domain.repository.SessionRepository
import io.reactivex.Completable
import javax.inject.Inject

class SessionDataRepository @Inject constructor(private val factory: SessionDataStoreFactory,
                                                private val postMapper: UserMapper) : SessionRepository {

    override fun login(loginParam: Login.LoginParam): Completable =
            factory.retrieveRemoteDataStore().doLogin(loginParam)
                    .ignoreElement()

}