package com.udacity.asteroidradar.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.udacity.asteroidradar.data.model.Asteroid

@Dao
interface AsteroidDao {

    @Query("SELECT * FROM asteroids")
    fun getAllAsteroids() : LiveData<List<Asteroid>>

    @Insert(onConflict = REPLACE)
    suspend fun addAllAsteroids(vararg asteroid: Asteroid)

    @Query("DELETE FROM asteroids")
    suspend fun deleteAll()

    @Query("SELECT * FROM asteroids WHERE id = :id LIMIT 1")
    suspend fun getAsteroid(id:Long) : LiveData<Asteroid>

}