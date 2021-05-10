package com.app.demo.model

import java.lang.Exception

sealed class Response<out R> {

    data class Success<out T>(val data: T) : Response<T>()
    data class Failure<out T>(private val failureValue: Any, val data: T? = null) : Response<T>() {
        val failure: SimpleError
            get() = getSimpleError()

        private fun getSimpleError(): SimpleError {
            return try {
                failureValue as SimpleError
            } catch (e: java.lang.Exception){
                SimpleError("", "")
            }
        }
    }
    data class Error(val exception: Exception) : Response<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failure<*> -> "Failure[failure=$failure, data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}

/**
 * `true` if [Result] is of episodeType [Success] & holds non-null [Success.data].
 */
val Response<*>.succeeded
    get() = this is Response.Success && data != null
