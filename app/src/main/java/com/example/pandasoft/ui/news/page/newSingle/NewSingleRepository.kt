package com.example.pandasoft.ui.news.page.newSingle

import com.example.pandasoft.api.APIService
import com.example.pandasoft.ui.login.model.RefreshTokenBodyRequest
import com.example.pandasoft.ui.login.model.RefreshTokenResponse
import com.example.pandasoft.ui.login.model.create
import com.example.pandasoft.ui.news.model.LikeBodyRequest
import com.example.pandasoft.util.AppExecutors
import com.example.pandasoft.ui.news.model.NewsResponse
import com.example.pandasoft.ui.news.model.create
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class NewSingleRepository(val appExecutors: AppExecutors,
                          val apiService : APIService
) {
    fun doLike(newsID : Int)  {
        var obb = apiService.sendLikeNews(LikeBodyRequest().create(newsID))
    }

    fun doRefreshToken(refreshToken : String) : Observable<Response<RefreshTokenResponse>>{
        var obb = apiService.sendRequestRefreshToken (RefreshTokenBodyRequest().create(refreshToken))
        return obb
    }
}