package com.edwin.randompicture.cache.mapper

import com.edwin.randompicture.cache.model.CachedPost
import com.edwin.randompicture.data.model.PostEntity
import javax.inject.Inject

class PostEntityMapper @Inject constructor() : EntityMapper<CachedPost, PostEntity> {

    override fun mapFromCached(type: CachedPost) =
            PostEntity(id = type.id,
                    imgPath = type.imgPath,
                    text = type.text,
                    timeStamp = type.timeStamp)

    override fun mapToCached(type: PostEntity) =
            CachedPost(id = type.id,
                    imgPath = type.imgPath,
                    text = type.text,
                    timeStamp = type.timeStamp)

}