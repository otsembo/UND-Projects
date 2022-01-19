package com.udacity.asteroidradar.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.udacity.asteroidradar.data.model.Asteroid

@Dao
interface AsteroidDao {

    @Query("SELECT * FROM asteroids ORDER BY approach_date ASC")
    fun getAllAsteroids() : LiveData<List<Asteroid>>

    @Insert(onConflict = REPLACE)
    suspend fun addAllAsteroids(vararg asteroid: Asteroid)

    @Query("DELETE FROM asteroids")
    suspend fun deleteAll()

    @Query("SELECT * FROM asteroids WHERE id = :id LIMIT 1")
    suspend fun getAsteroid(id:Long) : Asteroid

    @Query("SELECT * FROM asteroids WHERE approach_date = :approachDate")
    fun getTodayAsteroids(approachDate:String) : LiveData<List<Asteroid>>

}