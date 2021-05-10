package com.app.demo.model

import java.lang.Exception

sealed class ResultWithData<out R> {

    data class Success<out T>(val data: T) : ResultWithData<T>()
    data class Failure<out T>(private val failureValue: Any, val data: T? = null) : ResultWithData<T>(){
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
    data class Error(val exception: Exception) : ResultWithData<Nothing>()
    object Loading : ResultWithData<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Failure[failure=$failure, data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}

/**
 * `true` if [ResultWithData] is of episodeType [Success] & holds non-null [Success.data].
 */
val ResultWithData<*>.succeeded
    get() = this is ResultWithData.Success && data != null

