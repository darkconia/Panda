package com.example.pandasoft.ui.login

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.pandasoft.util.PreferenceData

class LoginViewModel(private val repo: LoginRepository , var pref : SharedPreferences) : ViewModel(){

    fun doLogin(user : String , pass : String ){
        repo.doLogin(user , pass)
            .subscribe({ loginRespond ->
                if(loginRespond.body()?.status == 200){
                    Log.d("login" , "${loginRespond.body()!!.toString()}")
                }
            })

    }

}