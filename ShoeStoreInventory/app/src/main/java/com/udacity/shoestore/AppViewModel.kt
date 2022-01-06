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

    val shoeSize = MutableLiveData<String>()
    val actualShoeSize : LiveData<String> = shoeSize

    init {
        _loginState.value = false
        _shoeData.value = ArrayList()
    }

    fun addShoeToList(shoe: Shoe) {
        val shoeList = ArrayList<Shoe>()
        _shoeData.value?.let { shoeList.addAll(it) }
        shoeList.add(shoe)
        _shoeData.value = shoeList

    }

    fun convertStringToDouble(size:String) : Double{
        return size.toDouble()
    }



}