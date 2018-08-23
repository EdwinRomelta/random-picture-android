package com.edwin.randompicture.presentation.viewmodel.pendingpost

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.edwin.randompicture.domain.interactor.usecase.SavePendingPost
import com.edwin.randompicture.domain.model.PendingPost
import com.edwin.randompicture.presentation.R
import com.edwin.randompicture.presentation.data.Resource
import com.edwin.randompicture.presentation.viewmodel.BaseViewModel
import java.util.*
import javax.inject.Inject

class PendingPostViewModel @Inject constructor(
        private val savePendingPost: SavePendingPost) : BaseViewModel() {

    fun createPendingPost(imagePath: String, caption: String): LiveData<Resource<Unit>> {
        val liveData = MutableLiveData<Resource<Unit>>()
        liveData.postValue(Resource.loading())
        compositeDisposable.add(savePendingPost.execute(PendingPost(imagePath, caption, Date()))
                .subscribe(
                        {
                            liveData.postValue(Resource.success(Unit))
                        },
                        {
                            liveData.postValue(Resource.error(R.string.capture_errorprocessingimage))
                        }
                ))
        return liveData
    }
}