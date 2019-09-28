package com.example.pandasoft.di

import com.example.pandasoft.ui.login.LoginRepository
import com.example.pandasoft.ui.login.LoginViewModel
import com.example.pandasoft.ui.login.api.LoginAPIService
import com.example.pandasoft.ui.news.page.newList.NewListRepository
import com.example.pandasoft.ui.news.page.newList.NewListViewModel
import com.example.pandasoft.util.AppExecutors
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {

    single { AppExecutors() }
//    single { LoginAPIService() }
//    single <LoginAPIService>{LoginAPIService()}
    single { LoginRepository(get(), get()) }
    single { NewListRepository(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { NewListViewModel(get()) }
}