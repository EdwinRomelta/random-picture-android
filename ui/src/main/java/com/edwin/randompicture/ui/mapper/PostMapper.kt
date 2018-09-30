package com.edwin.randompicture.ui.mapper

import com.edwin.randompicture.presentation.model.PostView
import com.edwin.randompicture.ui.model.PostViewModel
import javax.inject.Inject

class PostMapper @Inject constructor() : Mapper<PostViewModel, PostView> {
    override fun mapToViewModel(type: PostView) =
            PostViewModel(
                    imgPath = type.imgPath,
                    text = type.text,
                    timeStamp = type.timeStamp)
}