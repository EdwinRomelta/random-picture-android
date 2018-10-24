package com.edwin.randompicture.domain.repository

import androidx.paging.DataSource
import com.edwin.randompicture.domain.interactor.usecase.GetAndSavePost
import com.edwin.randompicture.domain.model.Post
import io.reactivex.Flowable
import io.reactivex.Single

interface PostRepository {

    fun getPostDataSource(): Single<DataSource.Factory<Int, Post>>

    fun getPostsByParam(param: GetAndSavePost.GetPostParam): Flowable<List<Post>>

    fun getPosts(): Flowable<List<Post>>

    fun publishPost(post: Post): Single<Post>
}