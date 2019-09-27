package com.example.pandasoft.ui.login.model

import com.google.gson.annotations.SerializedName

data class RefreshTokenResponse(

	@field:SerializedName("access_token")
	val accessToken: String? = null,

	@field:SerializedName("refresh_token")
	val refreshToken: String? = null,

	@field:SerializedName("data")
	val data: Any? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("expires_in")
	val expiresIn: Int? = null,

	@field:SerializedName("status")
	val status: Int? = null
)