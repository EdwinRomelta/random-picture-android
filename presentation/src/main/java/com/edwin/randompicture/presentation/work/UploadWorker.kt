package com.edwin.randompicture.presentation.work

import android.content.Context
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.edwin.randompicture.domain.interactor.usecase.UploadPendingPost
import io.reactivex.Single
import javax.inject.Inject

class UploadWorker(context: Context, params: WorkerParameters)
    : BaseObservableWorker(context, params) {

    companion object {
        private const val KEY_PENDING_POST_ID = "pending-post-id"

        fun createWorker(idPendingPost: Long) =
                WorkManager.getInstance().enqueue(OneTimeWorkRequestBuilder<UploadWorker>()
                        .setInputData(Data.Builder()
                                .putLong(KEY_PENDING_POST_ID, idPendingPost)
                                .build())
                        .build())
    }


    @Inject
    lateinit var uploadPendingPost: UploadPendingPost

    override fun createWork(): Single<Result> =
            Single.just(inputData.getLong(KEY_PENDING_POST_ID, 0))
                    .flatMap { id ->
                        if (id != 0L)
                            uploadPendingPost.execute(id).map { Result.SUCCESS }
                        else
                            Single.just(Result.FAILURE)
                    }

}