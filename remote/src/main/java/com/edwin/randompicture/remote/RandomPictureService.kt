package com.edwin.randompicture.remote

import com.edwin.randompicture.remote.model.PostModel
import io.reactivex.Single
import retrofit2.http.GET

interface RandomPictureService {

    @GET("posts/")
    fun getPosts(): Single<List<PostModel>>
}