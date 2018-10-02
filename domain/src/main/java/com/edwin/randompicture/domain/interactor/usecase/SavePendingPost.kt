package com.edwin.randompicture.domain.interactor.usecase

import com.edwin.randompicture.domain.executor.PostExecutionThread
import com.edwin.randompicture.domain.executor.ThreadExecutor
import com.edwin.randompicture.domain.interactor.SingleUseCase
import com.edwin.randompicture.domain.model.PendingPost
import com.edwin.randompicture.domain.repository.PendingPostRepository
import io.reactivex.Single
import javax.inject.Inject


class SavePendingPost @Inject constructor(
        private val pendingPostRepository: PendingPostRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread) :
        SingleUseCase<Long, PendingPost>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: PendingPost): Single<Long> = pendingPostRepository.savePendingPost(params)

}