package com.edwin.randompicture.data.source.post

import android.arch.paging.DataSource
import com.edwin.randompicture.data.model.PostEntity
import com.edwin.randompicture.data.repository.post.PostDataStore
import com.edwin.randompicture.data.repository.post.PostRemote
import com.edwin.randompicture.domain.interactor.usecase.GetAndSavePost
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class PostRemoteDataStore @Inject constructor(
        private val postRemote: PostRemote) : PostDataStore {

    override fun getPost(): Flowable<List<PostEntity>> =
            postRemote.getPost()
                    .toFlowable()

    override fun getPostsByParam(param: GetAndSavePost.GetPostParam): Flowable<List<PostEntity>> {
        val startId = param.postId
        return if (startId != null) {
            postRemote.getPostsFromId(startId, param.limit)
                    .toFlowable()
        } else {
            postRemote.getPostWithLimit(param.limit)
                    .toFlowable()
        }
    }

    override fun getPostDataSource(): Single<DataSource.Factory<Int, PostEntity>> =
            throw UnsupportedOperationException()

    override fun savePost(postEntity: List<PostEntity>): Completable =
            throw UnsupportedOperationException()

    override fun clearSavePost(postEntity: List<PostEntity>): Completable =
            throw UnsupportedOperationException()

    override fun publishPost(postEntity: PostEntity): Single<PostEntity> =
            postRemote.publishPost(postEntity)
}