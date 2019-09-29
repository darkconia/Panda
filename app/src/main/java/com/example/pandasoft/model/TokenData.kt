package com.example.pandasoft.model

import com.example.pandasoft.ui.login.model.LoginResponse
import com.google.gson.annotations.SerializedName

data class TokenData(

	@field:SerializedName("access_token")
	var accessToken: String? = null,

	@field:SerializedName("refresh_token")
	var refreshToken: String? = null,

	@field:SerializedName("expires_in")
	var expiresIn: Int? = null
)

fun TokenData.create(loginRespond : LoginResponse) : TokenData{
	return this.apply {
		accessToken = loginRespond.accessToken
		refreshToken = loginRespond.refreshToken
		expiresIn = loginRespond.expiresIn
	}
}