package com.edwin.randompicture.presentation.viewmodel.post

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.edwin.randompicture.domain.interactor.usecase.GetAndSavePost
import com.edwin.randompicture.domain.interactor.usecase.GetPostDataSource
import com.edwin.randompicture.presentation.mapper.PostMapper
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
open class PostViewModelFactory @Inject constructor(
        private val getAndSavePost: GetAndSavePost,
        private val getPostDataSource: GetPostDataSource,
        private val postMapper: PostMapper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostsViewModel::class.java)) {
            return PostsViewModel(getAndSavePost, getPostDataSource, postMapper) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}