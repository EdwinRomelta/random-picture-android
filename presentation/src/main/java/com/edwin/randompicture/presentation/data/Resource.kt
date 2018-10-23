package com.edwin.randompicture.presentation.data

import android.support.annotation.StringRes
import com.edwin.randompicture.presentation.data.error.ErrorResource

open class Resource<out T> constructor(val status: ResourceState, val data: T?, @StringRes val errorResource: ErrorResource?) {

    companion object {
        fun <T> success(data: T): Resource<T> = Resource(ResourceState.SUCCESS, data, null)

        fun <T> error(errorResource: ErrorResource): Resource<T> = Resource(ResourceState.ERROR, null, errorResource)

        fun <T> loading(): Resource<T> = Resource(ResourceState.LOADING, null, null)

    }
}