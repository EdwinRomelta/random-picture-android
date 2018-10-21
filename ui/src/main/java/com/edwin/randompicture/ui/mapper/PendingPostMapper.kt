package com.edwin.randompicture.ui.mapper

import com.edwin.randompicture.presentation.model.PendingPostView
import com.edwin.randompicture.ui.model.PendingPostViewModel
import javax.inject.Inject

class PendingPostMapper @Inject constructor() : Mapper<PendingPostViewModel, PendingPostView> {

    override fun mapToViewModel(type: PendingPostView): PendingPostViewModel =
            PendingPostViewModel(
                    id = type.id,
                    imagePath = type.imagePath,
                    caption = type.caption,
                    status = type.status)
}