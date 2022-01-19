package com.udacity.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.common.AppUtils
import com.udacity.asteroidradar.common.AppUtils.getTodaysDate
import com.udacity.asteroidradar.common.AppUtils.getWeeksEnd
import com.udacity.asteroidradar.data.database.AsteroidDatabase.Companion.getDbInstance
import com.udacity.asteroidradar.data.repository.AsteroidRepository

class RefreshAsteroids(private val mCtx: Context, workerParameters: WorkerParameters)
    : CoroutineWorker(appContext =  mCtx, params = workerParameters){

    override suspend fun doWork(): Result {

        val db = getDbInstance(mCtx = mCtx)
        val repository = AsteroidRepository(db)

        return try {
            //remove existing asteroids
            repository.deleteExistingAsteroids()

            repository.fetchAsteroids(
                getTodaysDate(),
                getWeeksEnd()
            )

            Result.success()
        }catch (e: Exception){
            Result.retry()
        }

    }


    companion object{
        const val WORK = "RefreshAsteroids"
    }

}