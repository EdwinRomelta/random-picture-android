package com.edwin.randompicture.presentation.viewmodel.pendingpost

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.edwin.randompicture.domain.interactor.usecase.GetPendingPostDataSource
import com.edwin.randompicture.domain.interactor.usecase.SavePendingPost
import com.edwin.randompicture.presentation.mapper.PendingPostMapper
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
open class PendingPostViewModelFactory @Inject constructor(
        private val getPendingPostDataSource: GetPendingPostDataSource,
        private val savePendingPost: SavePendingPost,
        private val pendingPostMapper: PendingPostMapper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PendingPostsViewModel::class.java)) {
            return PendingPostsViewModel(getPendingPostDataSource, pendingPostMapper) as T
        }
        if (modelClass.isAssignableFrom(PendingPostViewModel::class.java)) {
            return PendingPostViewModel(savePendingPost) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}