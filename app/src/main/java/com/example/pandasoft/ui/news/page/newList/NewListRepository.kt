package com.example.pandasoft.ui.news.page.newList

import com.example.pandasoft.api.APIService
import com.example.pandasoft.util.AppExecutors
import com.example.pandasoft.ui.news.model.NewsResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class NewListRepository(val appExecutors: AppExecutors,
                        val apiService : APIService
) {

    fun getNews() : Observable<Response<NewsResponse>> {

        var obb = apiService.sendRequestNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .unsubscribeOn(Schedulers.io())

        return obb
    }
}