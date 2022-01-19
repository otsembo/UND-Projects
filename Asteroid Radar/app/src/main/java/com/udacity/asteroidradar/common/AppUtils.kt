package com.udacity.asteroidradar.common

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object AppUtils {

    const val DEFAULT_END_DATE = ""

    @SuppressLint("SimpleDateFormat")
    fun getTodaysDate() : String {
        val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT)
        return dateFormat.format(Date())
    }

    @SuppressLint("SimpleDateFormat")
    fun getWeeksEnd() : String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 7)
        val dateInWeek = calendar.time
        return SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT).format(dateInWeek)
    }

}