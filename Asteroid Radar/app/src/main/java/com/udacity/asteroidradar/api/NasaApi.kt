package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.data.dto.ImageOfDay
import org.json.JSONObject
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {

    @GET("neo/rest/v1/feed")
    suspend fun fetchAsteroids(
        @Query("start_date")
        startDate:String,

        @Query("end_date")
        endDate:String) : String

    @GET("planetary/apod")
    suspend fun fetchImageOfDay() : ImageOfDay

}

