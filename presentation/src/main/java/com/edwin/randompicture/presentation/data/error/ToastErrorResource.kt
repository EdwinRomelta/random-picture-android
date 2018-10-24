package com.edwin.randompicture.presentation.data.error

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

class ToastErrorResource(@StringRes private val stringRes: Int) : ErrorDialogResource() {

    override fun show(context: Context) {
        Toast.makeText(context, stringRes, Toast.LENGTH_SHORT).show()
    }

}