package com.example.pandasoft.ui.login.model

import com.google.gson.annotations.SerializedName

data class LoginBodyRequest(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)