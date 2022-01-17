package com.udacity.asteroidradar.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.data.database.AsteroidDatabase
import com.udacity.asteroidradar.data.model.Asteroid
import com.udacity.asteroidradar.data.model.PictureOfDay
import com.udacity.asteroidradar.domain.FetchFromDB
import com.udacity.asteroidradar.domain.FetchFromServer
import com.udacity.asteroidradar.domain.SaveToDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AsteroidRepository
    constructor(private val db:AsteroidDatabase){

    private val ioScope = CoroutineScope(Dispatchers.IO)

    private val defaultScope = CoroutineScope(Dispatchers.Default)

    // data for ui
    val asteroids : LiveData<List<Asteroid>> = FetchFromDB.GetAsteroids(db.asteroidDao).invoke()
    val picOfDay  : LiveData<PictureOfDay> = FetchFromDB.GetPicOfDay(db.pictureDao).invoke()

    //single asteroid
    private val _singleAsteroid = MutableLiveData<Asteroid>()
    val singleAsteroid : LiveData<Asteroid>
        get() = _singleAsteroid


    private suspend fun savePicOfDay(pictureOfDay: PictureOfDay){

        ioScope.launch {
            SaveToDB.AddPicOfDay(db.pictureDao).invoke(pictureOfDay)
        }

    }


    private suspend fun saveAsteroids(asteroids: ArrayList<Asteroid>){

        ioScope.launch {
            SaveToDB.AddAsteroid(db.asteroidDao).invoke(asteroids)
        }

    }

    suspend fun fetchAsteroids (startDate: String, endDate: String){

        defaultScope.launch {
            try {
                // get data from servers
                val data = FetchFromServer.FetchAsteroids().invoke(startDate, endDate)
                //add to db
                saveAsteroids(parseAsteroidsJsonResult(data))

            }catch (e: HttpException){
                //TODO: Add exception handling for fetching asteroids
            }
        }

    }


    suspend fun fetchPicOfDay() {

        defaultScope.launch {
            try {
                //get image from server
                val image = FetchFromServer.FetchImageOfDay().invoke()
                //add to db
                savePicOfDay(image)
            }catch (e: HttpException){
                //TODO: Add exception handling for fetching pic of day
            }
        }

    }

    suspend fun getAsteroid (id:Long) {
        ioScope.launch {
            _singleAsteroid.value = FetchFromDB.GetSingleAsteroid(db.asteroidDao).invoke(id).value
        }
    }




}