package com.edwin.randompicture.presentation.data

import android.support.annotation.StringRes

open class Resource<out T> constructor(val status: ResourceState, val data: T?, @StringRes val message: Int?) {

    companion object {
        fun <T> success(data: T): Resource<T> = Resource(ResourceState.SUCCESS, data, null)

        fun <T> error(message: Int): Resource<T> = Resource(ResourceState.ERROR, null, message)

        fun <T> loading(): Resource<T> = Resource(ResourceState.LOADING, null, null)

    }
}