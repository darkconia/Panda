package com.example.pandasoft.ui.news.api

import com.example.pandasoft.ui.login.model.LoginBodyRequest
import com.example.pandasoft.ui.login.model.LoginResponse
import com.example.pandasoft.ui.news.model.LikeBodyRequest
import com.example.pandasoft.ui.news.model.NewsResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NewsAPIService {
    @GET("https://5c065a3fc16e1200139479cc.mockapi.io/api/v1/news")
    fun sendRequestNews(): Observable<Response<NewsResponse>>

    @POST("http://5c065a3fc16e1200139479cc.mocka pi.io/api/v1/like")
    fun sendLikeNews(@Body body: LikeBodyRequest)
}