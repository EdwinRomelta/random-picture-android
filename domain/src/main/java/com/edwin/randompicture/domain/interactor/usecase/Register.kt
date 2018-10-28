package com.edwin.randompicture.domain.interactor.usecase

import com.edwin.randompicture.domain.executor.PostExecutionThread
import com.edwin.randompicture.domain.executor.ThreadExecutor
import com.edwin.randompicture.domain.interactor.CompletableUseCase
import com.edwin.randompicture.domain.repository.SessionRepository
import io.reactivex.Completable
import javax.inject.Inject

class Register @Inject constructor(
        private val sessionRepository: SessionRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread) :
        CompletableUseCase<Register.RegisterParam>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: RegisterParam): Completable =
            sessionRepository.register(params)

    inner class RegisterParam(
            val name: String,
            val email: String,
            val password: String)
}