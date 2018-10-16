package com.edwin.randompicture.presentation.model

import java.util.*

data class PostView(
        val id: String,
        val imgPath: String,
        val text: String,
        val timeStamp: Date)