package com.udacity.asteroidradar.domain

import com.udacity.asteroidradar.data.database.dao.AsteroidDao
import com.udacity.asteroidradar.data.database.dao.PictureDao

object FetchFromDB {

    class GetPicOfDay(private val dao: PictureDao) {
         operator fun invoke() = dao.getImageOfDay()
    }

    class GetAsteroids(private val dao: AsteroidDao){
         operator fun invoke() = dao.getAllAsteroids()
    }

    class GetSingleAsteroid(private val dao: AsteroidDao){
       suspend operator fun invoke(id:Long) = dao.getAsteroid(id)
    }

}