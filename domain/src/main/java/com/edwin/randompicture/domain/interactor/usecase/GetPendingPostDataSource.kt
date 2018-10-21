package com.edwin.randompicture.domain.interactor.usecase

import android.arch.paging.DataSource
import com.edwin.randompicture.domain.executor.PostExecutionThread
import com.edwin.randompicture.domain.executor.ThreadExecutor
import com.edwin.randompicture.domain.interactor.SingleUseCase
import com.edwin.randompicture.domain.model.PendingPost
import com.edwin.randompicture.domain.repository.PendingPostRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPendingPostDataSource @Inject constructor(
        private val pendingPostRepository: PendingPostRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread) :
        SingleUseCase<DataSource.Factory<Int, PendingPost>, Unit>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Unit): Single<DataSource.Factory<Int, PendingPost>> =
            pendingPostRepository.getPendingPostDataSource()

}