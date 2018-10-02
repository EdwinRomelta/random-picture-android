package com.edwin.randompicture.data.source.post

import com.edwin.randompicture.data.model.PostEntity
import com.edwin.randompicture.data.repository.post.PostDataStore
import com.edwin.randompicture.data.repository.post.PostRemote
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class PostRemoteDataStore @Inject constructor(
        private val postRemote: PostRemote) : PostDataStore {

    override fun getPost(): Flowable<List<PostEntity>> =
            postRemote.getPost().toFlowable()

    override fun publishPost(postEntity: PostEntity): Single<PostEntity> =
            postRemote.publishPost(postEntity)
}