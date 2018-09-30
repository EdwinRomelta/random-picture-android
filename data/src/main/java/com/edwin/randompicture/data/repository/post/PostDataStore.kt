package com.edwin.randompicture.data.repository.post

import com.edwin.randompicture.data.model.PostEntity
import io.reactivex.Flowable

interface PostDataStore {

    fun getPost(): Flowable<List<PostEntity>>
}