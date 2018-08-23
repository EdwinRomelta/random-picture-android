package com.edwin.randompicture.presentation.mapper

import android.net.Uri
import com.edwin.randompicture.domain.model.Photo
import com.edwin.randompicture.presentation.model.PhotoView
import java.io.File
import javax.inject.Inject

class PhotoMapper @Inject constructor() : Mapper<PhotoView, Photo> {
    override fun mapToView(type: Photo) = PhotoView(Uri.fromFile(File(type.folderPath, type.fileName)))
}