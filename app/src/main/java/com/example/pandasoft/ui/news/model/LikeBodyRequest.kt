package com.example.pandasoft.ui.news.model

import com.google.gson.annotations.SerializedName

data class LikeBodyRequest(

	@field:SerializedName("newsId")
	var newsId: Int? = null
)

fun LikeBodyRequest.create(newsID : Int ) : LikeBodyRequest {

	return try {
		this.apply {
			newsId = newsID
		}
	} catch (e: Exception) {
		this
	}
}