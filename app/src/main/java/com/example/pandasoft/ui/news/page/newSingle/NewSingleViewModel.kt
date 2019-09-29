package com.example.pandasoft.ui.news.page.newSingle

import androidx.lifecycle.ViewModel

class NewSingleViewModel(private val repo: NewSingleRepository) : ViewModel() {

    fun doLike(newID : Int){
        repo.doLike(newID)
    }
}