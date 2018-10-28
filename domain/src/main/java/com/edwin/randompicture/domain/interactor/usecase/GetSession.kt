package com.edwin.randompicture.domain.interactor.usecase

import com.edwin.randompicture.domain.executor.PostExecutionThread
import com.edwin.randompicture.domain.executor.ThreadExecutor
import com.edwin.randompicture.domain.interactor.FlowableUseCase
import com.edwin.randompicture.domain.model.Session
import com.edwin.randompicture.domain.repository.SessionRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetSession @Inject constructor(
        private val sessionRepository: SessionRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread) :
        FlowableUseCase<Session, Unit>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Unit?): Flowable<Session> =
            sessionRepository.getSession()

}