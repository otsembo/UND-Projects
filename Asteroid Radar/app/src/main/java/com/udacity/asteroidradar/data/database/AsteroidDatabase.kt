package com.udacity.asteroidradar.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.udacity.asteroidradar.common.Constants
import com.udacity.asteroidradar.data.database.dao.AsteroidDao
import com.udacity.asteroidradar.data.database.dao.PictureDao
import com.udacity.asteroidradar.data.model.Asteroid
import com.udacity.asteroidradar.data.model.PictureOfDay

@Database(entities = [PictureOfDay::class, Asteroid::class], version = 1)
abstract class AsteroidDatabase : RoomDatabase() {

    abstract val asteroidDao :AsteroidDao
    abstract val pictureDao  :PictureDao

    companion object{

        @Volatile
        private var INSTANCE: AsteroidDatabase? = null

        fun getDbInstance(mCtx:Context) : AsteroidDatabase{

            synchronized(this){
                val dbInstance = INSTANCE

                return dbInstance ?: Room.databaseBuilder(
                    mCtx.applicationContext,
                    AsteroidDatabase::class.java,
                    Constants.APP_DB_NAME
                ).fallbackToDestructiveMigration().build()
            }

        }

    }

}