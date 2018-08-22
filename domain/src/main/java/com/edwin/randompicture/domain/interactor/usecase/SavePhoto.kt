package com.edwin.randompicture.domain.interactor.usecase

import com.edwin.randompicture.domain.executor.PostExecutionThread
import com.edwin.randompicture.domain.executor.ThreadExecutor
import com.edwin.randompicture.domain.interactor.SingleUseCase
import com.edwin.randompicture.domain.model.Photo
import com.edwin.randompicture.domain.repository.PhotoRepository
import io.reactivex.Single
import javax.inject.Inject

class SavePhoto @Inject constructor(
        private val photoRepository: PhotoRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread) :
        SingleUseCase<Photo, ByteArray?>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: ByteArray?): Single<Photo> {
        return if (params != null) {
            photoRepository.savePhoto(Photo(byteArray = params))
        } else
            throw IllegalArgumentException("Empty Params")
    }

}