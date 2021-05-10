package com.app.demo.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.demo.data.StoreDataResponse
import com.app.demo.model.Response
import com.app.demo.model.ResultWithData
import com.app.demo.repository.UserDetailsRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class UserDetailsUseCase(private val userDetailsRepository: UserDetailsRepository):
        UseCase<LiveData<ResultWithData<StoreDataResponse>>>, Cancellable, CoroutineScope {

    private var job: Job? = null
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO


    override fun execute(): MutableLiveData<ResultWithData<StoreDataResponse>> {
        val result = MutableLiveData<ResultWithData<StoreDataResponse>>()
        launch(Dispatchers.IO) {
            val toPost = when (val res = userDetailsRepository.getUserDetails()) {
                is Response.Success -> {
                    ResultWithData.Success(res.data)
                }
                is Response.Failure -> {
                    ResultWithData.Failure(res.failure)
                }
                is Response.Error -> {
                    ResultWithData.Error(res.exception)
                }
            }
            result.postValue(toPost)
        }
        return result
    }

    override fun cancel() {
        if(coroutineContext.isActive)
            job?.cancel()
    }
}
