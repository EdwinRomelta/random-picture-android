package com.edwin.randompicture.presentation.viewmodel.photo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.edwin.randompicture.domain.interactor.usecase.SavePhoto
import com.edwin.randompicture.presentation.R
import com.edwin.randompicture.presentation.data.Resource
import com.edwin.randompicture.presentation.data.error.ToastErrorResource
import com.edwin.randompicture.presentation.mapper.PhotoMapper
import com.edwin.randompicture.presentation.model.PhotoView
import com.edwin.randompicture.presentation.viewmodel.BaseViewModel
import javax.inject.Inject

class PhotoViewModel @Inject constructor(private val savePhoto: SavePhoto,
                                         private val photoMapper: PhotoMapper) : BaseViewModel() {

    fun createPhoto(byteArray: ByteArray): LiveData<Resource<PhotoView>> {
        val photoLiveData = MutableLiveData<Resource<PhotoView>>()
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
        return photoLiveData
    }
}