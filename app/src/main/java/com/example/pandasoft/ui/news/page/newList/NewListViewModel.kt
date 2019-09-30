package com.example.pandasoft.ui.news.page.newList

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pandasoft.ui.news.model.DataItem
import com.example.pandasoft.ui.news.model.NewsResponse
import com.example.pandasoft.util.PreferenceData

class NewListViewModel (private val repo: NewListRepository , var pref : PreferenceData) : ViewModel(){

    var newsData = MutableLiveData<List<DataItem>>()
    var newsDataSelected = MutableLiveData<DataItem>()

    fun getNews(){
        repo.getNews()
            .subscribe(
                {newsResponse ->
                    newsResponse.body()?.let{
                        if(it.status == 200){
                            newsData.postValue(it.data!!)
                     }
                 }
                },
                {e ->
                    Log.e("news","$e")
                }
            )
    }

}