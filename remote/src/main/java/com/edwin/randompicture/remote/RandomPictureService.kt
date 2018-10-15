package com.edwin.randompicture.remote

import com.edwin.randompicture.remote.model.PostModel
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface RandomPictureService {

    @GET("posts/")
    fun getPosts(): Single<List<PostModel>>

    @GET("posts/")
    fun getPostsWithLimit(@Query("limit") limit: Int): Single<List<PostModel>>

    @GET("posts/")
    fun getPostsStartFrom(@Query("startId") startId: String, @Query("limit") limit: Int): Single<List<PostModel>>

    @Multipart
    @POST("post/")
    fun postPost(@Part file: MultipartBody.Part,
                 @Part("text") caption: RequestBody,
                 @Part("timestamp") createTimeStamp: RequestBody): Single<PostModel>
}