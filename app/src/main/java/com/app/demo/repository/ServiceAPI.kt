package com.app.demo.repository

import com.app.demo.data.StoreDataResponse
import retrofit2.Call
import retrofit2.http.GET

interface ServiceAPI {

    @GET("products/")
    fun getServiceUrl(): Call<StoreDataResponse>

}