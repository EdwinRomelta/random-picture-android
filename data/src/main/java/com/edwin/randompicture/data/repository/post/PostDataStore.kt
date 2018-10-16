package com.edwin.randompicture.data.repository.post

import android.arch.paging.DataSource
import com.edwin.randompicture.data.model.PostEntity
import com.edwin.randompicture.domain.interactor.usecase.GetAndSavePost
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface PostDataStore {

    fun getPost(): Flowable<List<PostEntity>>

    fun getPostsByParam(param: GetAndSavePost.GetPostParam): Flowable<List<PostEntity>>

    fun getPostDataSource(): Single<DataSource.Factory<Int, PostEntity>>

    fun publishPost(postEntity: PostEntity): Single<PostEntity>

    fun clearSavePost(postEntity: List<PostEntity>): Completable

    fun savePost(postEntity: List<PostEntity>): Completable

}