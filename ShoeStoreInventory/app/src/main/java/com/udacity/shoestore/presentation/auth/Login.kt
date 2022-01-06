package com.udacity.shoestore.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.AppViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.AuthLoginBinding

class Login : Fragment() {

    private lateinit var binding:AuthLoginBinding
    private val viewModel:AppViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.auth_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicks()
    }

    //initialize clicks
    private fun initClicks(){
        binding.btnLogin.setOnClickListener { navigateToWelcome(it) }
        binding.txtCreateAccount.setOnClickListener { navigateToWelcome(it) }
    }

    private fun navigateToWelcome(mView:View){
        val directions = LoginDirections.actionLoginToWelcome()
        mView.findNavController().navigate(directions)
    }


}