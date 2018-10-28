package com.edwin.randompicture.presentation.util

import android.util.Patterns

fun String.isEmail() = this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()