package com.edwin.randompicture.presentation.viewmodel.pendingpost

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.edwin.randompicture.domain.interactor.usecase.SavePendingPost
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
open class PendingPostViewModelFactory @Inject constructor(
        private val savePendingPost: SavePendingPost) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PendingPostViewModel::class.java)) {
            return PendingPostViewModel(savePendingPost) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}