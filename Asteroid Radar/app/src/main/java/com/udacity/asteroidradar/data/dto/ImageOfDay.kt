package com.udacity.asteroidradar.data.dto

import com.udacity.asteroidradar.data.model.PictureOfDay

data class ImageOfDay(
    val copyright: String,
    val date: String,
    val explanation: String,
    val hdurl: String,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String
)

fun ImageOfDay.getPictureOfDay() : PictureOfDay{
    return PictureOfDay(
        mediaType = media_type,
        title = title,
        url = url
    )
}