package com.edwin.randompicture.presentation.viewmodel.post

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.edwin.randompicture.domain.interactor.usecase.GetPost
import com.edwin.randompicture.domain.model.Post
import com.edwin.randompicture.presentation.R
import com.edwin.randompicture.presentation.data.Resource
import com.edwin.randompicture.presentation.mapper.PostMapper
import com.edwin.randompicture.presentation.model.PostView
import com.edwin.randompicture.presentation.viewmodel.BaseViewModel
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class PostsViewModel @Inject constructor(private val getPost: GetPost,
                                         private val postMapper: PostMapper) : BaseViewModel() {

    val postLiveData: LiveData<Resource<List<PostView>>>
        get() = postsLiveData
    private val postsLiveData: MutableLiveData<Resource<List<PostView>>> = MutableLiveData()

    init {
        fetchPosts()
    }


    fun fetchPosts() {
        postsLiveData.postValue(Resource.loading())
        return getPost.execute(BufferooSubscriber())
    }

    inner class BufferooSubscriber : DisposableSubscriber<List<Post>>() {

        override fun onComplete() {}

        override fun onNext(data: List<Post>) {
            postsLiveData.postValue(Resource.success(data.map { postMapper.mapToView(it) }))
        }

        override fun onError(exception: Throwable) {
            postsLiveData.postValue(Resource.error(R.string.posts_errorretriveposts))
        }

    }

    override fun onCleared() {
        super.onCleared()
        getPost.dispose()
    }
}