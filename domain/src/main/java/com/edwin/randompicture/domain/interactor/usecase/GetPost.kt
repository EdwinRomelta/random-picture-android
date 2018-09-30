package com.edwin.randompicture.domain.interactor.usecase

import com.edwin.randompicture.domain.executor.PostExecutionThread
import com.edwin.randompicture.domain.executor.ThreadExecutor
import com.edwin.randompicture.domain.interactor.FlowableUseCase
import com.edwin.randompicture.domain.model.Post
import com.edwin.randompicture.domain.repository.PostRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetPost @Inject constructor(
        private val postRepository: PostRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread) :
        FlowableUseCase<List<Post>, Void>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Void?): Flowable<List<Post>> =
            postRepository.getPosts()

}