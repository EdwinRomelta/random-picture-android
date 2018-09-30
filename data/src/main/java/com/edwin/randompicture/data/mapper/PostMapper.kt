package com.edwin.randompicture.data.mapper

import com.edwin.randompicture.data.model.PostEntity
import com.edwin.randompicture.domain.model.Post
import javax.inject.Inject

class PostMapper @Inject constructor() : Mapper<PostEntity, Post> {

    override fun mapFromEntity(type: PostEntity) =
            Post(id = type.id,
                    imgPath = type.imgPath,
                    text = type.text,
                    timeStamp = type.timeStamp)

    override fun mapToEntity(type: Post) =
            PostEntity(id = type.id,
                    imgPath = type.imgPath,
                    text = type.text,
                    timeStamp = type.timeStamp)
}