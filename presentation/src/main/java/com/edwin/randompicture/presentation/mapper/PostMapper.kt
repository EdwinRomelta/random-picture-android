package com.edwin.randompicture.presentation.mapper

import com.edwin.randompicture.domain.model.Post
import com.edwin.randompicture.presentation.model.PostView
import javax.inject.Inject

class PostMapper @Inject constructor() : Mapper<PostView, Post> {

    override fun mapToView(type: Post) =
            PostView(
                    imgPath = type.imgPath,
                    text = type.text,
                    timeStamp = type.timeStamp)
}