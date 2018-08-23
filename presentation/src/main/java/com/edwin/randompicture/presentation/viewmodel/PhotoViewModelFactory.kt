package com.edwin.randompicture.presentation.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.edwin.randompicture.domain.interactor.usecase.SavePhoto
import com.edwin.randompicture.presentation.mapper.PhotoMapper
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
open class PhotoViewModelFactory @Inject constructor(
        private val savePhoto: SavePhoto,
        private val photoMapper: PhotoMapper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotoViewModel::class.java)) {
            return PhotoViewModel(savePhoto, photoMapper) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}