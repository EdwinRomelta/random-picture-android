package com.edwin.randompicture.domain.interactor.usecase

import androidx.paging.DataSource
import com.edwin.randompicture.domain.executor.PostExecutionThread
import com.edwin.randompicture.domain.executor.ThreadExecutor
import com.edwin.randompicture.domain.interactor.SingleUseCase
import com.edwin.randompicture.domain.model.Post
import com.edwin.randompicture.domain.repository.PostRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPostDataSource @Inject constructor(
        private val postRepository: PostRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread) :
        SingleUseCase<DataSource.Factory<Int, Post>, Unit>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Unit): Single<DataSource.Factory<Int, Post>> =
            postRepository.getPostDataSource()

}