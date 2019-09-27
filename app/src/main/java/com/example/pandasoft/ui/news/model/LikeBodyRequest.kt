package com.example.pandasoft.ui.news.model

import com.google.gson.annotations.SerializedName

data class LikeBodyRequest(

	@field:SerializedName("newsId")
	val newsId: Int? = null
)