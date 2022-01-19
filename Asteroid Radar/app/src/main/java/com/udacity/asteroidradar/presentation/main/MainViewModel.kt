package com.udacity.asteroidradar.presentation.main

import android.app.Application
import androidx.lifecycle.*
import com.udacity.asteroidradar.common.AppUtils.DEFAULT_END_DATE
import com.udacity.asteroidradar.common.AppUtils.getTodaysDate
import com.udacity.asteroidradar.common.AppUtils.getWeeksEnd
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
    val filteredAsteroids = repo.filteredAsteroids


    //navigate to details
    private val _navigateToAsteroidDetails = MutableLiveData<Asteroid?>()
    val navigateToAsteroidDetails : LiveData<Asteroid?>
        get() = _navigateToAsteroidDetails

    //loading details
    private val _isDataLoading = MutableLiveData<Boolean?>()
    val isDataLoading : LiveData<Boolean?>
        get() = _isDataLoading


    init {
        repo.fetchAsteroids(
            getTodaysDate(),
            getWeeksEnd()
        )

        repo.fetchPicOfDay()
    }

    fun onAsteroidClicked(asteroid: Asteroid){
        _navigateToAsteroidDetails.value = asteroid
    }

    fun finishedNavigation(){
        _navigateToAsteroidDetails.value = null
    }

    fun filterAsteroids(start: String, end:String){

        repo.filterAsteroids(startDate = start, endDate = end)

    }

    fun resetList(){
        repo.showAllAsteroids()
    }

    fun finishedLoading(){
        _isDataLoading.value = false
    }

    fun startLoading(){
        _isDataLoading.value = true
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