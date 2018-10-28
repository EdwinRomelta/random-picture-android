package com.edwin.randompicture.presentation.data

import androidx.annotation.StringRes
import com.edwin.randompicture.domain.exception.DomainLayerException
import com.edwin.randompicture.presentation.R
import com.edwin.randompicture.presentation.data.error.ErrorResource
import com.edwin.randompicture.presentation.data.error.ToastErrorResource

open class Resource<out T> constructor(val status: ResourceState, val data: T?, @StringRes val errorResource: ErrorResource?) {

    companion object {
        fun <T> success(data: T): Resource<T> = Resource(ResourceState.SUCCESS, data, null)

        fun <T> error(errorResource: ErrorResource): Resource<T> = Resource(ResourceState.ERROR, null, errorResource)

        fun <T> error(throwable: Throwable, @StringRes defaultMessage: Int = R.string.all_defaulterror): Resource<T> {
            //TODO better error handling from data, cache, and remote layer
            if (throwable.cause == null && throwable.message != null) {
                //This are only from data, cache, and remote layer
                val message = throwable.message
                if (message != null) {
                    return error(ToastErrorResource(message))
                }

            }
            if (throwable is DomainLayerException) {
                val message = throwable.message
                if (message != null) {
                    return error(ToastErrorResource(message))
                }
            }
            return error(ToastErrorResource(defaultMessage))
        }

        fun <T> loading(): Resource<T> = Resource(ResourceState.LOADING, null, null)

    }
}