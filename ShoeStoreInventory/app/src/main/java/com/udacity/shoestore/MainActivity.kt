package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private val viewModel:AppViewModel by viewModels()

    //is user logged in
    private var isLoggedIn:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        Timber.plant(Timber.DebugTree())

        setUpAppBar()
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

    //set up app bar
    private fun setUpAppBar(){

        setSupportActionBar(binding.toolbar)

        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHost.navController
        val appBarConfig = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfig)
    }


}
