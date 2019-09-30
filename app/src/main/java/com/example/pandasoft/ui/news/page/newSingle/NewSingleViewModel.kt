package com.example.pandasoft.ui.news.page.newSingle

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.pandasoft.util.DateTimeConverter
import com.example.pandasoft.util.PreferenceData

class NewSingleViewModel(
    private val repo: NewSingleRepository,
    var pref: PreferenceData,
    var dateUtil: DateTimeConverter
) : ViewModel() {

    fun doLike(newID: Int) {
        if (!doCheckTokenExpire()) { // not expire yet
            repo.doLike(newID)
        } else {
            repo.doRefreshToken( pref.getShareConfig(pref.refreshToken) as String)
                .subscribe (
                    {refreshResponse ->
                        refreshResponse.body()?.let {
//                            pref.addShareConfig(pref.accessToken , "${it.accessToken}")
//                            pref.addShareConfig(pref.refreshToken , "${it.refreshToken}")
//                            pref.addShareConfig(pref.expireIn , it.expiresIn!!.toInt())
//                            pref.addShareConfig(pref.expireDateTime , dateUtil.getExpireDateTimeStr(it.expiresIn!!.toInt()))

                            pref.addShareConfig(pref.accessToken , "access_2")
                            pref.addShareConfig(pref.refreshToken , "refresh_2")
                            pref.addShareConfig(pref.expireIn , 60)
                            pref.addShareConfig(pref.expireDateTime , dateUtil.getExpireDateTimeStr(it.expiresIn!!.toInt()))
                            doLike(newID)
                        }

                    },
                    {e ->
                        Log.e("refresh","$e")
                    }
                )
        }
    }

    fun doCheckTokenExpire(): Boolean {
//        return false
        return dateUtil.getDateAfterIsAfter(dateUtil.getCurrentDateTimeStr(), pref.getShareConfig(pref.expireDateTime) as String)
    }
}