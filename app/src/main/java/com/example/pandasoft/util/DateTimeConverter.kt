package com.example.pandasoft.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateTimeConverter {

    var dateFormat = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.US)

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


    fun getCurrentDateTimeLong():Long{
        return java.util.Date().time
    }

    fun getCurrentDateTimeStr(): String {
        return Date(getCurrentDateTimeLong()).toString()
    }

    fun getExpireDateTimeStr(time : Int): String {
        var expireDateTime = getCurrentDateTimeLong() + time*1000
        return Date(expireDateTime).toString()
    }

    fun getDateAfterIsAfter(dateMustBefore : String , dateMustAfter : String):Boolean{

        var dateAfterIsAfter = false

        var dateBefore: Date? = null
        var dateAfter: Date? = null

        if ("" != dateMustBefore && "" != dateMustAfter) {
            try {
                dateBefore = dateFormat.parse(dateMustBefore)
                dateAfter = dateFormat.parse(dateMustAfter)

                if (dateAfter!!.after(dateBefore!!)) {
                    dateAfterIsAfter = true
                }

            } catch (e: ParseException) {
                e.printStackTrace()
            }

        } else if ("" != dateMustBefore && "" == dateMustAfter) {
            dateAfterIsAfter = true
        }

        return dateAfterIsAfter
    }
}