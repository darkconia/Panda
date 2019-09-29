package com.example.pandasoft.util

import android.view.View

interface OnItemClickListener<T> {
    fun onClick(v: View, data: T)
}