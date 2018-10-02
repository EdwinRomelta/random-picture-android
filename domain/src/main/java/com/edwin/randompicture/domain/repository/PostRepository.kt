package com.edwin.randompicture.domain.repository

import com.edwin.randompicture.domain.model.Post
import io.reactivex.Flowable
import io.reactivex.Single

interface PostRepository {

    fun getPosts(): Flowable<List<Post>>

    fun publishPost(post: Post): Single<Post>
}