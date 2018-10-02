package com.edwin.randompicture.remote.thirdparty.okhttp

import com.edwin.randompicture.remote.DATE_FORMAT
import okhttp3.MediaType
import okhttp3.RequestBody
import java.text.SimpleDateFormat
import java.util.*

fun String.toRequestBody(): RequestBody = RequestBody.create(MediaType.parse("text/plain"), this)

private val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.US)

fun Date.toRequestBody(): RequestBody = dateFormat.format(this).toRequestBody()