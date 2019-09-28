package com.example.pandasoft.ui.login.model

import com.google.gson.annotations.SerializedName

data class LoginBodyRequest(

	@field:SerializedName("password")
	var password: String? = null,

	@field:SerializedName("username")
	var username: String? = null


)

fun LoginBodyRequest.create(user:String , pass:String) : LoginBodyRequest {

	return try {
		this.apply {
			username = user
			password = pass
		}
	}catch (e:Exception){
		this
	}

}