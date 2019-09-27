package com.example.pandasoft.ui.login.api
import com.example.pandasoft.ui.login.model.LoginBodyRequest
import com.example.pandasoft.ui.login.model.LoginResponse
import com.example.pandasoft.ui.login.model.RefreshTokenBodyRequest
import com.example.pandasoft.ui.login.model.RefreshTokenResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface LoginAPIService {

    @POST("https://5c065a3fc16e1200139479cc.mockapi.io/api/v1/login")
    fun sendRequestLogin(@Body body: LoginBodyRequest): Observable<Response<LoginResponse>>

    @POST("https://5c065a3fc16e1200139479cc.mockapi.io/api/v1/refresh")
    fun sendRequestRefreshToken(@Body body: RefreshTokenBodyRequest): Observable<Response<RefreshTokenResponse>>
}