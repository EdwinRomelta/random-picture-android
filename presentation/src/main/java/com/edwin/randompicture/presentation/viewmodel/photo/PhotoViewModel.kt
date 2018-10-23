package com.edwin.randompicture.presentation.viewmodel.photo

import android.arch.lifecycle.LiveData
import com.edwin.randompicture.domain.interactor.usecase.SavePhoto
import com.edwin.randompicture.presentation.R
import com.edwin.randompicture.presentation.data.ActionLiveData
import com.edwin.randompicture.presentation.data.Resource
import com.edwin.randompicture.presentation.data.error.ToastErrorResource
import com.edwin.randompicture.presentation.mapper.PhotoMapper
import com.edwin.randompicture.presentation.model.PhotoView
import com.edwin.randompicture.presentation.viewmodel.BaseViewModel
import javax.inject.Inject

class PhotoViewModel @Inject constructor(private val savePhoto: SavePhoto,
                                         private val photoMapper: PhotoMapper) : BaseViewModel() {

    private val photoLiveData = ActionLiveData<Resource<PhotoView>>()
    val photo: LiveData<Resource<PhotoView>> = photoLiveData

    fun createPhoto(byteArray: ByteArray) {
        photoLiveData.postValue(Resource.loading())
        compositeDisposable.add(savePhoto.execute(byteArray)
                .subscribe(
                        {
                            photoLiveData.postValue(Resource.success(photoMapper.mapToView(it)))
                        },
                        {
                            photoLiveData.postValue(Resource.error(ToastErrorResource(R.string.capture_errorprocessingimage)))
                        }
                ))
    }
}