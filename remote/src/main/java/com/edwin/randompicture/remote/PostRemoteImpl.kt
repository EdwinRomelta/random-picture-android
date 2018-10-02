package com.edwin.randompicture.remote

import com.edwin.randompicture.data.model.PostEntity
import com.edwin.randompicture.data.repository.post.PostRemote
import com.edwin.randompicture.remote.mapper.PostMapper
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject


class PostRemoteImpl @Inject constructor(private val randomPictureService: RandomPictureService,
                                         private val postMapper: PostMapper) : PostRemote {

    override fun getPost(): Single<List<PostEntity>> =
            randomPictureService.getPosts()
                    .map { postModelList ->
                        postModelList.map { postMapper.mapFromRemote(it) }
                    }

    override fun publishPost(postEntity: PostEntity): Single<PostEntity> {
        val uploadFile = File(postEntity.imgPath)
        val requestFile = RequestBody.create(MediaType.parse("image/jpeg"), uploadFile)
        val file = MultipartBody.Part.createFormData("image", uploadFile.name, requestFile)

        return randomPictureService.postPost(file = file,
                caption = postEntity.text,
                createTimeStamp = postEntity.timeStamp)
                .map { postMapper.mapFromRemote(it) }
    }
}