package com.example.pandasoft.api

import com.example.pandasoft.ui.login.model.LoginBodyRequest
import com.example.pandasoft.ui.login.model.LoginResponse
import com.example.pandasoft.ui.login.model.RefreshTokenBodyRequest
import com.example.pandasoft.ui.login.model.RefreshTokenResponse
import com.example.pandasoft.ui.news.model.LikeBodyRequest
import com.example.pandasoft.ui.news.model.LikeBodyRespond
import com.example.pandasoft.ui.news.model.NewsResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {
    @POST("login")
    fun sendRequestLogin(@Body body: LoginBodyRequest): Observable<Response<LoginResponse>>

    @POST("refresh")
    fun sendRequestRefreshToken(@Body body: RefreshTokenBodyRequest): Observable<Response<RefreshTokenResponse>>

    @GET("news")
    fun sendRequestNews(): Observable<Response<NewsResponse>>

    @POST("like")
    fun sendLikeNews(@Body body: LikeBodyRequest): Observable<Response<LikeBodyRespond>>
}