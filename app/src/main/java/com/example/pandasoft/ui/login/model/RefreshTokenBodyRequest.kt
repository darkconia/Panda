package com.example.pandasoft.ui.login.model

import com.google.gson.annotations.SerializedName

data class RefreshTokenBodyRequest(

	@field:SerializedName("refresh_token")
	val refreshToken: String? = null
)