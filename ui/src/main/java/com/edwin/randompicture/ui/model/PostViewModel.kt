package com.edwin.randompicture.ui.model

import android.text.format.DateUtils
import java.util.*

class PostViewModel(
        val imgPath: String,
        val text: String,
        val timeStamp: Date) {

    val relativeDate: CharSequence = DateUtils.getRelativeTimeSpanString(timeStamp.time, Date().time, DateUtils.MINUTE_IN_MILLIS)
}