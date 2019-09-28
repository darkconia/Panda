package com.example.pandasoft.ui.login

import android.util.Log
import com.example.pandasoft.ui.login.api.LoginAPIService
import com.example.pandasoft.ui.login.model.LoginBodyRequest
import com.example.pandasoft.ui.login.model.LoginResponse
import com.example.pandasoft.ui.login.model.create
import com.example.pandasoft.util.AppExecutors
import com.example.pandasoft.util.NetworkBoundResourceObserver
import com.example.pandasoft.util.NoNetworkException
import io.reactivex.Observable
import retrofit2.Response
import java.util.concurrent.TimeoutException

class LoginRepository (val appExecutors: AppExecutors ,
                       val apiService : LoginAPIService){

    interface LoginCallback{
        fun onLoginSuccess(loginResponse: LoginResponse)
        fun onLoginFail(msg : String)
    }

    fun doLogin(username : String , password : String , callback: LoginCallback){

        var loginBodyReqest = LoginBodyRequest().create(username, password)

        NetworkBoundResourceObserver(appExecutors, object : NetworkBoundResourceObserver.Callback<LoginResponse, Any>() {

            override fun onResponse(code: Int, message: String, item: LoginResponse?) {

                item?.let {
                    if(it.status == 200){
                        Log.d("login" , "${item.toString()}")
                        callback.onLoginSuccess(item)
                    }
                }
            }

            override fun onCreateObservable(): Observable<Response<LoginResponse>> {

                return apiService.sendRequestLogin(loginBodyReqest)
            }

            override fun onNetworkUnavailable(t: NoNetworkException) {

            }

            override fun onTimeout(): Long {

                return 300
            }

            override fun onUnauthorized() {
            }

            override fun onError(t: Throwable) {
            }

            override fun onWarning(t: Throwable) {
            }

            override fun onTimeoutException(e: TimeoutException) {
            }

        })
    }
}