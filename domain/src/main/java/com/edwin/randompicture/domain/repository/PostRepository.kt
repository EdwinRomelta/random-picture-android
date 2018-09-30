package com.edwin.randompicture.domain.repository

import com.edwin.randompicture.domain.model.Post
import io.reactivex.Flowable

interface PostRepository {

    fun getPosts(): Flowable<List<Post>>
}