package com.app.demo.model

import androidx.annotation.Keep

@Keep
data class SimpleError(val errorCode: String = "-1", val errorMessage: String = "")