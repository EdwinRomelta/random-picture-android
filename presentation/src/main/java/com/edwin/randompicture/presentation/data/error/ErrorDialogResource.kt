package com.edwin.randompicture.presentation.data.error

import android.content.Context

abstract class ErrorDialogResource : ErrorResource {

    abstract fun show(context: Context)
}

fun ErrorResource.show(context: Context) {
    if (this is ErrorDialogResource) {
        show(context)
    }
}