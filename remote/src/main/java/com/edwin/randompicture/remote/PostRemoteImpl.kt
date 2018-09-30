package com.edwin.randompicture.remote

import com.edwin.randompicture.data.model.PostEntity
import com.edwin.randompicture.data.repository.post.PostRemote
import com.edwin.randompicture.remote.mapper.PostMapper
import io.reactivex.Single
import javax.inject.Inject

class PostRemoteImpl @Inject constructor(private val randomPictureService: RandomPictureService,
                                         private val postMapper: PostMapper) : PostRemote {

    override fun getPost(): Single<List<PostEntity>> =
            randomPictureService.getPosts()
                    .map { postModelList ->
                        postModelList.map { postMapper.mapFromRemote(it) }
                    }

}