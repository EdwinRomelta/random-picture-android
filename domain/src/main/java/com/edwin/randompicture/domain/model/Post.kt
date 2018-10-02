package com.edwin.randompicture.domain.model

import java.util.*

const val POST_PUBLISH_ID = ""

data class Post(val id: String,
                val imgPath: String,
                val text: String,
                val timeStamp: Date)