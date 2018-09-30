package com.edwin.randompicture.remote.mapper

import com.edwin.randompicture.data.model.PostEntity
import com.edwin.randompicture.remote.model.PostModel
import javax.inject.Inject

class PostMapper @Inject constructor() : EntityMapper<PostModel, PostEntity> {

    override fun mapFromRemote(type: PostModel) =
            PostEntity(id = type.id,
                    imgPath = type.imgPath,
                    text = type.text,
                    timeStamp = type.timeStamp)
}