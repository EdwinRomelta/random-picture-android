package com.edwin.randompicture.domain.interactor.usecase

import com.edwin.randompicture.domain.executor.PostExecutionThread
import com.edwin.randompicture.domain.executor.ThreadExecutor
import com.edwin.randompicture.domain.interactor.SingleUseCase
import com.edwin.randompicture.domain.model.POST_PUBLISH_ID
import com.edwin.randompicture.domain.model.Post
import com.edwin.randompicture.domain.repository.PendingPostRepository
import com.edwin.randompicture.domain.repository.PostRepository
import io.reactivex.Single
import javax.inject.Inject

class UploadPendingPost @Inject constructor(
        private val pendingPostRepository: PendingPostRepository,
        private val postRepository: PostRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread) :
        SingleUseCase<Post, Long>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Long): Single<Post> =
            pendingPostRepository.getPendingPostById(params)
                    .map {
                        Post(id = POST_PUBLISH_ID,
                                imgPath = it.imagePath,
                                timeStamp = it.createdDate,
                                text = it.caption)
                    }
                    .flatMapSingle { postRepository.publishPost(it) }
                    .singleOrError()

}