package com.edwin.randompicture.presentation.viewmodel.pendingpost

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.net.Uri
import com.edwin.randompicture.domain.interactor.usecase.SavePendingPost
import com.edwin.randompicture.domain.model.PendingPost
import com.edwin.randompicture.presentation.R
import com.edwin.randompicture.presentation.data.Resource
import com.edwin.randompicture.presentation.viewmodel.BaseViewModel
import com.edwin.randompicture.presentation.work.UploadWorker
import io.reactivex.observers.DisposableSingleObserver
import java.util.*
import javax.inject.Inject

class PendingPostViewModel @Inject constructor(
        private val savePendingPost: SavePendingPost) : BaseViewModel() {

    fun createPendingPost(imagePath: String, caption: String): LiveData<Resource<Unit>> {
        val liveData = MutableLiveData<Resource<Unit>>()
        liveData.postValue(Resource.loading())
        savePendingPost.execute(PendingPostSubscriber(liveData), PendingPost(Uri.parse(imagePath).path, caption, Date()))
        return liveData
    }

    override fun onCleared() {
        super.onCleared()
        savePendingPost.dispose()
    }

    inner class PendingPostSubscriber(private val liveData: MutableLiveData<Resource<Unit>>) : DisposableSingleObserver<Long>() {
        override fun onSuccess(id: Long) {
            id.let { UploadWorker.createWorker(it) }
            liveData.postValue(Resource.success(Unit))
        }

        override fun onError(exception: Throwable) {
            liveData.postValue(Resource.error(R.string.capture_errorprocessingimage))
        }
    }
}