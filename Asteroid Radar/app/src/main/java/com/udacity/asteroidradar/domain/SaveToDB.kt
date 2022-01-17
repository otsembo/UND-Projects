package com.udacity.asteroidradar.domain

import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.data.database.dao.AsteroidDao
import com.udacity.asteroidradar.data.database.dao.PictureDao
import com.udacity.asteroidradar.data.dto.ImageOfDay
import com.udacity.asteroidradar.data.model.Asteroid
import com.udacity.asteroidradar.data.model.PictureOfDay

object SaveToDB  {

    class AddPicOfDay(private val dao:PictureDao) {
        suspend operator fun invoke(pictureOfDay: PictureOfDay) {
            //remove current image
            dao.deleteImage()
            //insert images
            dao.addImageOfDay(pictureOfDay = pictureOfDay)
        }
    }

    class AddAsteroid(private val dao: AsteroidDao){
        suspend operator fun invoke(asteroids: ArrayList<Asteroid>){
            //add all asteroids
            dao.addAllAsteroids(*asteroids.toTypedArray())
        }
    }

}