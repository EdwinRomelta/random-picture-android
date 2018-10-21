package com.edwin.randompicture.domain.interactor.usecase

import com.edwin.randompicture.domain.executor.PostExecutionThread
import com.edwin.randompicture.domain.executor.ThreadExecutor
import com.edwin.randompicture.domain.interactor.SingleUseCase
import com.edwin.randompicture.domain.model.POST_PUBLISH_ID
import com.edwin.randompicture.domain.model.PendingPost
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
                    .flatMapSingle { pendingPost ->
                        val uploadPendingPost = pendingPost.copy(status = PendingPost.STATUS_UPLOADING)
                        pendingPostRepository.savePendingPost(uploadPendingPost).map { uploadPendingPost }
                    }
                    .flatMapSingle { pendingPost ->
                        val uploadPost = Post(id = POST_PUBLISH_ID,
                                imgPath = pendingPost.imagePath,
                                timeStamp = pendingPost.createdDate,
                                text = pendingPost.caption)
                        postRepository.publishPost(uploadPost)
                                .flatMap { uploadedPost ->
                                    pendingPostRepository.deletePendingPost(pendingPost).toSingle { uploadedPost }
                                }
                                .onErrorResumeNext {
                                    val uploadPendingPost = pendingPost.copy(status = PendingPost.STATUS_FAILED)
                                    pendingPostRepository.savePendingPost(uploadPendingPost)
                                    Single.error(it)
                                }
                    }
                    .singleOrError()

}