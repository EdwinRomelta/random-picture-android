package com.edwin.randompicture.presentation.viewmodel.pendingpost

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.edwin.randompicture.domain.interactor.usecase.GetPendingPostDataSource
import com.edwin.randompicture.presentation.mapper.PendingPostMapper
import com.edwin.randompicture.presentation.viewmodel.BaseViewModel
import javax.inject.Inject

class PendingPostsViewModel @Inject constructor(
        getPendingPostDataSource: GetPendingPostDataSource,
        pendingPostMapper: PendingPostMapper) : BaseViewModel() {

    companion object {
        private const val PAGE_SIZE = 5
    }

    val pendingPost = LivePagedListBuilder(
            getPendingPostDataSource.executeSync(Unit).map { pendingPostMapper.mapToView(it) },
            PagedList.Config.Builder()
                    .setPageSize(PAGE_SIZE)
                    .build())
            .build()
}