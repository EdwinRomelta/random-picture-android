package com.edwin.randompicture.data.repository.post

import android.arch.paging.DataSource
import com.edwin.randompicture.data.model.PostEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface PostCache {

    fun getPost(): Flowable<List<PostEntity>>

    fun getPostDataSource(): Single<DataSource.Factory<Int, PostEntity>>

    fun savePost(postEntity: List<PostEntity>): Completable
}