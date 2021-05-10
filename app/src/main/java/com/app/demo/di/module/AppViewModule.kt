package com.app.demo.di.module

import com.app.demo.repository.ServiceAPI
import com.app.demo.repository.UserDetailsRepository
import com.app.demo.usecase.UserDetailsUseCase
import com.app.demo.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appViewModule = module{
        factory { UserDetailsRepository(provideServiceApi(get())) }
        factory { UserDetailsUseCase(get()) }
        viewModel { UserViewModel(get()) }
}


fun provideServiceApi(retrofit: Retrofit): ServiceAPI = retrofit.create(ServiceAPI::class.java)

