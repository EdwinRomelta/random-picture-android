package com.edwin.randompicture.data.source.post

import android.arch.paging.DataSource
import com.edwin.randompicture.data.model.PostEntity
import com.edwin.randompicture.data.repository.post.PostCache
import com.edwin.randompicture.data.repository.post.PostDataStore
import com.edwin.randompicture.domain.interactor.usecase.GetAndSavePost
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class PostCacheDataStore @Inject constructor(
        private val postCache: PostCache) : PostDataStore {

    override fun getPostDataSource(): Single<DataSource.Factory<Int, PostEntity>> =
            postCache.getPostDataSource()

    override fun getPostsByParam(param: GetAndSavePost.GetPostParam): Flowable<List<PostEntity>> =
            throw UnsupportedOperationException()

    override fun getPost(): Flowable<List<PostEntity>> =
            postCache.getPost()

    override fun savePost(postEntity: List<PostEntity>): Completable =
            postCache.savePost(postEntity)

    override fun publishPost(postEntity: PostEntity): Single<PostEntity> =
            throw UnsupportedOperationException()
}