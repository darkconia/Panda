package com.example.pandasoft.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.pandasoft.model.TokenData
import com.example.pandasoft.model.create
import com.example.pandasoft.ui.login.model.LoginResponse
import com.example.pandasoft.util.DateTimeConverter
import com.example.pandasoft.util.PreferenceData
import com.google.gson.GsonBuilder
import org.koin.android.ext.android.get

class LoginViewModel(private val repo: LoginRepository , var pref : PreferenceData , var dateUtil : DateTimeConverter) : ViewModel(){

    fun doLogin(user : String , pass : String ){
        repo.doLogin(user , pass)
            .subscribe({ loginRespond ->
                if(loginRespond.body()?.status == 200){
                    setDataInPreference(loginRespond.body()!!)
                }
            })

        /*pref.addShareConfig(pref.accessToken , "access_1")
        pref.addShareConfig(pref.refreshToken , "refresh_1")
        pref.addShareConfig(pref.expireIn , 60)
        pref.addShareConfig(pref.expireDateTime , dateUtil.getExpireDateTimeStr(60))
        Log.d("preference : ","${pref.getShareConfig(pref.expireDateTime).toString()}")*/
    }

    fun setDataInPreference(loginResponse: LoginResponse){
        pref.addShareConfig(pref.accessToken , "${loginResponse.accessToken}")
        pref.addShareConfig(pref.refreshToken , "${loginResponse.refreshToken}")
        pref.addShareConfig(pref.expireIn , "${loginResponse.expiresIn!!.toInt()}")
        pref.addShareConfig(pref.expireDateTime , dateUtil.getExpireDateTimeStr(loginResponse.expiresIn!!.toInt()))
    }
}