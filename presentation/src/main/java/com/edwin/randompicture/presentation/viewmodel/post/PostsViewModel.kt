package com.edwin.randompicture.presentation.viewmodel.post

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.Transformations.switchMap
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.edwin.randompicture.domain.interactor.usecase.GetAndSavePost
import com.edwin.randompicture.domain.interactor.usecase.GetPostDataSource
import com.edwin.randompicture.presentation.data.Listing
import com.edwin.randompicture.presentation.data.NetworkState
import com.edwin.randompicture.presentation.data.PagingRequestHelper
import com.edwin.randompicture.presentation.data.createStatusLiveData
import com.edwin.randompicture.presentation.mapper.PostMapper
import com.edwin.randompicture.presentation.model.PostView
import com.edwin.randompicture.presentation.viewmodel.BaseViewModel
import java.util.concurrent.Executors
import javax.inject.Inject

class PostsViewModel @Inject constructor(private val getAndSavePost: GetAndSavePost,
                                         private val getPostDataSource: GetPostDataSource,
                                         private val postMapper: PostMapper) : BaseViewModel() {

    companion object {
        private const val NETWORK_PAGE_SIZE = 3
    }

    private val listing = MutableLiveData<Listing<PostView>>()
    val posts: LiveData<PagedList<PostView>> = switchMap(listing) { it.pagedList }
    val networkState: LiveData<NetworkState> = switchMap(listing) { it.networkState }
    val refreshState: LiveData<NetworkState> = switchMap(listing) { it.refreshState }

    init {
        val boundaryCallback = PostBoundaryCallback()
        val refreshTrigger = MutableLiveData<Unit>()
        val refreshState = Transformations.switchMap(refreshTrigger) {
            doRefresh()
        }

        val livePagedList = LivePagedListBuilder(
                getPostDataSource.executeSync(Unit).map { postMapper.mapToView(it) },
                PagedList.Config.Builder()
                        .setPageSize(NETWORK_PAGE_SIZE)
                        .build())
                .setBoundaryCallback(boundaryCallback)
                .build()

        listing.value = Listing(
                pagedList = livePagedList,
                networkState = boundaryCallback.networkState,
                retry = {
                    boundaryCallback.helper.retryAllFailed()
                },
                refresh = {
                    refreshTrigger.value = null
                },
                refreshState = refreshState
        )
        refresh()
    }

    override fun onCleared() {
        super.onCleared()
        getPostDataSource.dispose()
    }

    fun refresh() {
        listing.value?.refresh?.invoke()
    }

    private fun doRefresh(): LiveData<NetworkState> {
        val networkState = MutableLiveData<NetworkState>()
        networkState.value = NetworkState.LOADING
        compositeDisposable.add(getAndSavePost.execute(getAndSavePost.GetPostParam(NETWORK_PAGE_SIZE))
                .subscribe(
                        {
                            networkState.postValue(NetworkState.LOADED)
                        }
                        , { networkState.value = NetworkState.error(it.message) }
                ))
        return networkState
    }

    inner class PostBoundaryCallback : PagedList.BoundaryCallback<PostView>() {

        val helper = PagingRequestHelper(Executors.newSingleThreadExecutor())
        val networkState = helper.createStatusLiveData()

        override fun onZeroItemsLoaded() {
            helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) { callback ->
                compositeDisposable.add(getAndSavePost.execute(getAndSavePost.GetPostParam(NETWORK_PAGE_SIZE))
                        .subscribe({ callback.recordSuccess() },
                                { callback.recordFailure(it) }))
            }
        }

        override fun onItemAtEndLoaded(itemAtEnd: PostView) {
            helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) { callback ->
                compositeDisposable.add(getAndSavePost.execute(
                        getAndSavePost.GetPostParam(NETWORK_PAGE_SIZE, itemAtEnd.id))
                        .subscribe({ callback.recordSuccess() },
                                { callback.recordFailure(it) }))
            }
        }
    }
}