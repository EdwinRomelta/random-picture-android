package com.edwin.randompicture.data.repository.post

import com.edwin.randompicture.data.model.PostEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface PostCache {

    fun getPost(): Flowable<List<PostEntity>>

    fun savePost(postEntity: List<PostEntity>): Completable
}