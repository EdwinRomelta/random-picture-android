package com.edwin.randompicture.remote

import com.edwin.randompicture.remote.model.PostModel
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface RandomPictureService {

    @GET("posts/")
    fun getPosts(): Single<List<PostModel>>

    @Multipart
    @POST("post/")
    fun postPost(@Part file: MultipartBody.Part,
                 @Part("text") caption: RequestBody,
                 @Part("timestamp") createTimeStamp: RequestBody): Single<PostModel>
}