package com.edwin.randompicture.presentation.mapper

import com.edwin.randompicture.domain.model.PendingPost
import com.edwin.randompicture.presentation.model.PendingPostView
import javax.inject.Inject

class PendingPostMapper @Inject constructor() : Mapper<PendingPostView, PendingPost> {

    override fun mapToView(type: PendingPost) =
            PendingPostView(
                    id = type.id
                            ?: throw NullPointerException("Showing pending post with empty id"),
                    imagePath = type.imagePath,
                    caption = type.caption,
                    status = type.status)
}