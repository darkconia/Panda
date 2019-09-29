package com.example.pandasoft.util

import java.text.SimpleDateFormat
import java.util.*

class DateTimeConverter {

    fun getDateTime(timeStamp: String): String? {
        try {
            val dateformat = SimpleDateFormat("d/MM/yyyy")
            val netDate = Date(timeStamp.toLong()*1000)
            Date()
            return dateformat.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }
}