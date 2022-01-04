package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class AppViewModel : ViewModel() {

    private val _loginState = MutableLiveData<Boolean>()
    val loginState : LiveData<Boolean> = _loginState

    private val _shoeData = MutableLiveData<ArrayList<Shoe>>()
    val shoeData : LiveData<ArrayList<Shoe>> = _shoeData

    init {
        _loginState.value = false
    }

}