package com.edwin.randompicture.data.repository.post

import androidx.paging.DataSource
import com.edwin.randompicture.data.model.PostEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface PostCache {

    fun getPost(): Flowable<List<PostEntity>>

    fun getPostDataSource(): Single<DataSource.Factory<Int, PostEntity>>

    fun clearSavePost(postEntity: List<PostEntity>): Completable

    fun savePost(postEntity: PostEntity): Completable

    fun savePosts(postEntity: List<PostEntity>): Completable
}