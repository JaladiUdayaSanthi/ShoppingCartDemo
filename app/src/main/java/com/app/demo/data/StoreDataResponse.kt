package com.app.demo.data


import com.google.gson.annotations.SerializedName

class StoreDataResponse : ArrayList<StoreDataResponseItem>()

data class StoreDataResponseItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("description")
    val description: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("image")
    val image: String
)