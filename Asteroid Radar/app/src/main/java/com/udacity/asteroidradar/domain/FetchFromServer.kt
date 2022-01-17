package com.udacity.asteroidradar.domain

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.common.ApiKeys
import com.udacity.asteroidradar.common.Constants
import com.udacity.asteroidradar.data.dto.getPictureOfDay
import com.udacity.asteroidradar.data.model.PictureOfDay
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object FetchFromServer {

    object NetworkService {

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        private val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        val API:NasaApi = retrofit.create(NasaApi::class.java)

    }


    class FetchAsteroids {
        suspend operator fun invoke(startDate:String, endDate:String) : String {
            return NetworkService.API.fetchAsteroids(
                startDate = startDate,
                endDate = endDate,
                apiKey = ApiKeys.NASA_API
            )
        }
    }


    class FetchImageOfDay {
        suspend operator fun invoke() : PictureOfDay {
            return NetworkService.API.fetchImageOfDay(
                apiKey = ApiKeys.NASA_API
            ).getPictureOfDay()
        }
    }



}