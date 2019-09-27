package com.example.pandasoft.ui.news.model

import com.google.gson.annotations.SerializedName

data class DataItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("create")
	val create: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("detail")
	val detail: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("uuid")
	val uuid: String? = null
)