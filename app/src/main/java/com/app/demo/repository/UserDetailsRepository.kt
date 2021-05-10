package com.app.demo.repository

import com.app.demo.data.StoreDataResponse
import com.app.demo.model.Response
import retrofit2.await
import java.lang.Exception

class UserDetailsRepository constructor(private val serviceAPI: ServiceAPI) {

    suspend fun getUserDetails(): Response<StoreDataResponse> {
            return try {
                val result  = serviceAPI.getServiceUrl().await()
                return Response.Success(result)
            } catch (e: Exception) {
                Response.Error(e)
            }
    }
}