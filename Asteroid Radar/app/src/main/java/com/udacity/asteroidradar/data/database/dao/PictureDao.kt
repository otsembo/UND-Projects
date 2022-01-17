package com.udacity.asteroidradar.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.udacity.asteroidradar.data.dto.ImageOfDay
import com.udacity.asteroidradar.data.model.PictureOfDay

@Dao
interface PictureDao {

    @Query("SELECT * FROM image_of_day LIMIT 1")
    fun getImageOfDay() : LiveData<PictureOfDay>

    @Query("DELETE FROM image_of_day")
    suspend fun deleteImage()

    @Insert(onConflict = REPLACE)
    suspend fun addImageOfDay(pictureOfDay: PictureOfDay)

}