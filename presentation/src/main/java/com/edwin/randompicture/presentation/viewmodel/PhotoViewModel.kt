package com.edwin.randompicture.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.edwin.randompicture.domain.interactor.usecase.SavePhoto
import com.edwin.randompicture.presentation.R
import com.edwin.randompicture.presentation.data.Resource
import com.edwin.randompicture.presentation.mapper.PhotoMapper
import com.edwin.randompicture.presentation.model.PhotoView
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
                            photoLiveData.postValue(Resource.error(R.string.capture_errorprocessingimage))
                        }
                ))
        return photoLiveData
    }
}