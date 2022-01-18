package com.udacity.asteroidradar.presentation.main

import android.app.Application
import androidx.lifecycle.*
import com.udacity.asteroidradar.data.database.AsteroidDatabase.Companion.getDbInstance
import com.udacity.asteroidradar.data.model.Asteroid
import com.udacity.asteroidradar.data.repository.AsteroidRepository
import kotlinx.coroutines.launch

class MainViewModel (application: Application) : AndroidViewModel(application) {


    // database object
    private val db = getDbInstance(application)
    //repo
    private val repo = AsteroidRepository(db)

    val heroImage = repo.picOfDay
    val asteroids = repo.asteroids


    //navigate to details
    private val _navigateToAsteroidDetails = MutableLiveData<Asteroid?>()
    val navigateToAsteroidDetails : LiveData<Asteroid?>
        get() = _navigateToAsteroidDetails


    init {
        repo.fetchAsteroids("","")
        repo.fetchPicOfDay()
    }

    fun onAsteroidClicked(asteroid: Asteroid){
        _navigateToAsteroidDetails.value = asteroid
    }

    fun finishedNavigation(){
        _navigateToAsteroidDetails.value = null
    }

    fun filterAsteroids(start: String, end:String){
        
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory{

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MainViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(application) as T
            }
            throw IllegalArgumentException("Unable to create view model")
        }

    }

}