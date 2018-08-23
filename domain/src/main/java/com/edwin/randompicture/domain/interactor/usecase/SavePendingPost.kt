package com.edwin.randompicture.domain.interactor.usecase

import com.edwin.randompicture.domain.executor.PostExecutionThread
import com.edwin.randompicture.domain.executor.ThreadExecutor
import com.edwin.randompicture.domain.interactor.CompletableUseCase
import com.edwin.randompicture.domain.model.PendingPost
import com.edwin.randompicture.domain.repository.PendingPostRepository
import io.reactivex.Completable
import javax.inject.Inject


class SavePendingPost @Inject constructor(
        private val pendingPostRepository: PendingPostRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread) :
        CompletableUseCase<PendingPost>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: PendingPost): Completable = pendingPostRepository.savePendingPost(params)

}