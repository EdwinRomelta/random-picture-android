package com.edwin.randompicture.remote.model

import com.google.gson.annotations.SerializedName
import java.util.*

class PostModel(
        @SerializedName("id")
        val id: String,
        @SerializedName("imgPath")
        val imgPath: String,
        @SerializedName("text")
        val text: String,
        @SerializedName("timestamp")
        val timeStamp: Date)