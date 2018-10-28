package com.edwin.randompicture.domain.interactor.usecase

import com.edwin.randompicture.domain.executor.PostExecutionThread
import com.edwin.randompicture.domain.executor.ThreadExecutor
import com.edwin.randompicture.domain.interactor.CompletableUseCase
import com.edwin.randompicture.domain.repository.SessionRepository
import io.reactivex.Completable
import javax.inject.Inject

class Logout @Inject constructor(
        private val sessionRepository: SessionRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread) :
        CompletableUseCase<Unit>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Unit): Completable =
            sessionRepository.logout()

}