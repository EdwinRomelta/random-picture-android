package com.edwin.randompicture.domain.interactor.usecase

import com.edwin.randompicture.domain.executor.PostExecutionThread
import com.edwin.randompicture.domain.executor.ThreadExecutor
import com.edwin.randompicture.domain.interactor.CompletableUseCase
import com.edwin.randompicture.domain.repository.PostRepository
import io.reactivex.Completable
import javax.inject.Inject

class GetAndSavePost @Inject constructor(
        private val postRepository: PostRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread) :
        CompletableUseCase<GetAndSavePost.GetPostParam>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: GetPostParam): Completable =
            postRepository.getPostsByParam(params).ignoreElements()

    inner class GetPostParam(val limit: Int,
                             val postId: String? = null)
}