package com.edwin.randompicture.presentation.viewmodel.pendingpost

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.edwin.randompicture.domain.interactor.usecase.SavePendingPost
import com.edwin.randompicture.domain.model.PendingPost
import com.edwin.randompicture.presentation.R
import com.edwin.randompicture.presentation.data.Resource
import com.edwin.randompicture.presentation.data.error.ToastErrorResource
import com.edwin.randompicture.presentation.data.error.ValidationErrorResource
import com.edwin.randompicture.presentation.model.PendingPostView
import com.edwin.randompicture.presentation.viewmodel.BaseViewModel
import com.edwin.randompicture.presentation.work.UploadWorker
import io.reactivex.observers.DisposableSingleObserver
import java.util.*
import javax.inject.Inject

class PendingPostViewModel @Inject constructor(
        private val savePendingPost: SavePendingPost) : BaseViewModel() {

    companion object {
        const val EMPTY_CAPTION = 10001
    }

    private val pendingPostLiveData = MutableLiveData<PendingPostView>()
    val pendingPost: LiveData<PendingPostView> = pendingPostLiveData

    private val uploadPendingPostLiveData = MutableLiveData<Resource<Unit>>()
    val uploadPendingPost: LiveData<Resource<Unit>> = uploadPendingPostLiveData

    fun submitPendingPost(imagePath: String, caption: String?) {
        if (caption == null || caption.isEmpty()) {
            uploadPendingPostLiveData.postValue(Resource.error(ValidationErrorResource(EMPTY_CAPTION)))
            return
        }
        savePendingPost.execute(PendingPostSubscriber(),
                PendingPost(Uri.parse(imagePath).path, caption, Date()))
    }

    fun createPendingPost(imagePath: String) {
        pendingPostLiveData.postValue(PendingPostView(imagePath = imagePath))
    }

    override fun onCleared() {
        super.onCleared()
        savePendingPost.dispose()
    }

    inner class PendingPostSubscriber : DisposableSingleObserver<Long>() {
        override fun onSuccess(id: Long) {
            id.let { UploadWorker.createWorker(it) }
            uploadPendingPostLiveData.postValue(Resource.success(Unit))
        }

        override fun onError(exception: Throwable) {
            uploadPendingPostLiveData.postValue(Resource.error(ToastErrorResource(R.string.capture_errorprocessingimage)))
        }
    }
}