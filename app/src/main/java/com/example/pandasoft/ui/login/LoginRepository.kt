package com.example.pandasoft.ui.login

import com.example.pandasoft.api.APIService
import com.example.pandasoft.ui.login.model.LoginBodyRequest
import com.example.pandasoft.ui.login.model.LoginResponse
import com.example.pandasoft.ui.login.model.create
import com.example.pandasoft.util.AppExecutors
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class LoginRepository (val appExecutors: AppExecutors ,
                       val apiService : APIService){

    fun doLogin(username : String , password : String) : Observable<Response<LoginResponse>> {

        var obb = apiService.sendRequestLogin(LoginBodyRequest().create(username, password))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .unsubscribeOn(Schedulers.io())

        return obb
    }
}