package com.example.pandasoft.ui.login

import androidx.lifecycle.ViewModel
import com.example.pandasoft.ui.login.model.LoginResponse

class LoginViewModel(private val repo: LoginRepository) : ViewModel(){


    fun doLogin(user : String , pass : String ){
        repo.doLogin(user , pass , object : LoginRepository.LoginCallback {
            override fun onLoginSuccess(loginResponse: LoginResponse) {

            }

            override fun onLoginFail(msg: String) {

            }

        })
    }


}