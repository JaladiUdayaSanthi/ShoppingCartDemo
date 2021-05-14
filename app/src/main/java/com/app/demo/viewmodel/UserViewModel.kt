package com.app.demo.viewmodel

import androidx.lifecycle.*
import com.app.demo.data.StoreDataResponse
import com.app.demo.data.StoreDataResponseItem
import com.app.demo.model.ResultWithData
import com.app.demo.model.SimpleError
import com.app.demo.usecase.UserDetailsUseCase


class UserViewModel(private val userDetailsUseCase: UserDetailsUseCase): ViewModel() {

    private val _userList: MutableLiveData<StoreDataResponse> = MutableLiveData()
    val userList: LiveData<StoreDataResponse>
        get() = _userList

    private lateinit var storeData:StoreDataResponseItem


    private val _addItemList: MutableLiveData<List<StoreDataResponseItem>> = MutableLiveData()
    val addItemList: LiveData<List<StoreDataResponseItem>>
        get() = _addItemList

    init {
        getUserDetails()
    }

    private fun getUserDetails() {
        val data = userDetailsUseCase.execute()
        data.observeForever {
            when (it) {
                is ResultWithData.Success -> _userList.postValue(it.data)
                is ResultWithData.Error -> {
                    SimpleError("------ Error -----","------- Error in json -------")
                }
                else -> {

                }
            }
        }
    }

    fun updateStoreItemData(data:StoreDataResponseItem){
        storeData = data
    }

    fun getStoreItemData() = storeData


    fun addItemToCart(item: StoreDataResponseItem) {
        _addItemList.postValue(listOf(item))
    }

}


