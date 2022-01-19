package com.udacity.asteroidradar

import android.app.Application
import com.udacity.asteroidradar.domain.FetchFromServer
import com.udacity.asteroidradar.work.RefreshAsteroids
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class AsteroidApplication : Application() {

    //coroutine scope
    private val defaultScope = CoroutineScope(Dispatchers.Default)
    private val asteroidWorker = FetchFromServer.ServerWorker(defaultScope)

    override fun onCreate() {
        super.onCreate()
        //start worker
        asteroidWorker()
    }



}