package com.udacity.asteroidradar.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.data.database.AsteroidDatabase.Companion.getDbInstance
import com.udacity.asteroidradar.data.repository.AsteroidRepository
import kotlinx.coroutines.launch

class MainViewModel (application: Application) : AndroidViewModel(application) {


    // database object
    private val db = getDbInstance(application)
    //repo
    private val repo = AsteroidRepository(db)

    val heroImage = repo.picOfDay
    val asteroids = repo.asteroids


    init {

        viewModelScope.launch {
            repo.fetchAsteroids("","")
            repo.fetchPicOfDay()
        }

    }



    class Factory(private val application: Application) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MainViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(application) as T
            }
            throw IllegalArgumentException("Unable to create view model")
        }

    }

}