package com.edwin.randompicture.mapper

import android.net.Uri
import com.edwin.randompicture.domain.model.Photo
import com.edwin.randompicture.model.PhotoView
import java.io.File
import javax.inject.Inject

class PhotoMapper @Inject constructor() : Mapper<PhotoView, Photo> {
    override fun mapToView(type: Photo) = PhotoView(Uri.fromFile(File(type.folderPath, type.fileName)))
}