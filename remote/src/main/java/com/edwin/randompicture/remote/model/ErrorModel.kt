package com.edwin.randompicture.remote.model

import com.google.gson.annotations.SerializedName

class ErrorModel(
        @SerializedName("message")
        val message: String)