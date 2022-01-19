package com.udacity.asteroidradar.domain

import android.os.Build
import androidx.work.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.common.ApiKeys
import com.udacity.asteroidradar.common.Constants
import com.udacity.asteroidradar.data.dto.getPictureOfDay
import com.udacity.asteroidradar.data.model.PictureOfDay
import com.udacity.asteroidradar.work.RefreshAsteroids
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object FetchFromServer {

    object NetworkService {

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        private val client = OkHttpClient.Builder()
            .addInterceptor { interceptorChain ->
                val url = interceptorChain
                    .request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("api_key", ApiKeys.NASA_API)
                    .build()
                interceptorChain.proceed(interceptorChain.request().newBuilder().url(url).build())
            }.build()

        private val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
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
                endDate = endDate
            )
        }
    }


    class FetchImageOfDay {
        suspend operator fun invoke() : PictureOfDay {
            return NetworkService.API.fetchImageOfDay().getPictureOfDay()
        }
    }


    class ServerWorker(private val scope: CoroutineScope) {
        operator fun invoke() = scope.launch {
            //work constraints
            val workerConstraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresCharging(true)
                .setRequiresBatteryNotLow(true)
                .apply {
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        setRequiresDeviceIdle(true)
                    }
                }.build()

            val workRequest = PeriodicWorkRequestBuilder<RefreshAsteroids>(1, TimeUnit.DAYS)
                .setConstraints(workerConstraints)
                .build()


            WorkManager.getInstance().enqueueUniquePeriodicWork(
                RefreshAsteroids.WORK,
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest
            )

        }
    }



}