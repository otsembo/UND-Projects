package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private val viewModel:AppViewModel by viewModels()

    //is user logged in
    private var isLoggedIn:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.plant(Timber.DebugTree())

        setUpObservers()
    }

    private fun setUpObservers(){
        viewModel.loginState.observe(this, {
            isLoggedIn = it
        })
    }

    override fun onResume() {
        super.onResume()
        handleLoginState()

    }

    private fun handleLoginState(){
        if(isLoggedIn){

        }
    }

}
