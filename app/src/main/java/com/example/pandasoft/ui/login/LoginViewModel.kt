package com.example.pandasoft.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.pandasoft.model.TokenData
import com.example.pandasoft.model.create
import com.example.pandasoft.util.DateTimeConverter
import com.example.pandasoft.util.PreferenceData
import com.google.gson.GsonBuilder
import org.koin.android.ext.android.get

class LoginViewModel(private val repo: LoginRepository , var pref : PreferenceData , var dateUtil : DateTimeConverter) : ViewModel(){

    fun doLogin(user : String , pass : String ){
        /*repo.doLogin(user , pass)
            .subscribe({ loginRespond ->
                if(loginRespond.body()?.status == 200){
                    Log.d("login" , "${loginRespond.body()!!.toString()}")
                    var token = TokenData().create(loginRespond.body()!!)

                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val jsonPref: String = gson.toJson(token)

                    pref["preferenceData" ,jsonPref]
                }
            })*/

        var token = TokenData("access_1" , "refresh_1" ,60 )
        val gson = GsonBuilder().setPrettyPrinting().create()
        val jsonPref: String = gson.toJson(token)

        pref.addShareConfig(pref.accessToken , "access_1")
        pref.addShareConfig(pref.refreshToken , "refresh_1")
        pref.addShareConfig(pref.expireIn , 60)
        pref.addShareConfig(pref.expireDateTime , dateUtil.getExpireDateTimeStr(60))
        Log.d("preference : ","${pref.getShareConfig(pref.expireDateTime).toString()}")
    }

}