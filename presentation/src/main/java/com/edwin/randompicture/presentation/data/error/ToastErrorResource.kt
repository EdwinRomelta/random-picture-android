package com.edwin.randompicture.presentation.data.error

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

class ToastErrorResource private constructor(
        private val message: String?,
        private val stringResId: Int?) : ErrorDialogResource() {

    constructor(message: String) : this(message, null)

    constructor(@StringRes stringResId: Int) : this(null, stringResId)

    override fun show(context: Context) {
        if (message.isNullOrEmpty() && stringResId != null) {
            Toast.makeText(context, context.resources.getString(stringResId), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

}