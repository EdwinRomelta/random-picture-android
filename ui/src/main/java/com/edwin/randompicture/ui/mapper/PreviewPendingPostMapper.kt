package com.edwin.randompicture.ui.mapper

import com.edwin.randompicture.presentation.model.PendingPostView
import com.edwin.randompicture.ui.model.PreviewPendingPostViewModel
import javax.inject.Inject

class PreviewPendingPostMapper @Inject constructor() : Mapper<PreviewPendingPostViewModel, PendingPostView> {

    override fun mapToViewModel(type: PendingPostView): PreviewPendingPostViewModel =
            PreviewPendingPostViewModel(
                    imagePath = type.imagePath,
                    caption = type.caption)
}