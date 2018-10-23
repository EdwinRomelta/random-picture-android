package com.edwin.randompicture.presentation.data.error

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast

class ToastErrorResource(@StringRes private val stringRes: Int) : ErrorDialogResource() {

    override fun show(context: Context) {
        Toast.makeText(context, stringRes, Toast.LENGTH_SHORT).show()
    }

}