package com.edwin.randompicture.ui.mapper

import com.edwin.randompicture.presentation.model.PhotoView
import com.edwin.randompicture.ui.model.PhotoViewModel
import javax.inject.Inject

class PhotoMapper @Inject constructor() : Mapper<PhotoViewModel, PhotoView> {
    override fun mapToViewModel(type: PhotoView): PhotoViewModel {
        val filePath = type.filePath
        if (filePath != null)
            return PhotoViewModel(filePath)
        throw IllegalArgumentException("Empty file path")
    }
}