package com.example.pandasoft.ui.login.model

import com.google.gson.annotations.SerializedName

data class RefreshTokenBodyRequest(

	@field:SerializedName("refresh_token")
	var refreshToken: String? = null
)

fun RefreshTokenBodyRequest.create(tokenToRefresh : String):RefreshTokenBodyRequest{
	return this.apply {
		refreshToken = tokenToRefresh
	}
}