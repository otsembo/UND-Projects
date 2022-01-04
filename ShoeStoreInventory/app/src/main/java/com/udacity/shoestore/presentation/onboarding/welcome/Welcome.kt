package com.udacity.shoestore.presentation.onboarding.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.OnboardingWelcomeBinding

class Welcome : Fragment() {

    private lateinit var binding:OnboardingWelcomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = OnboardingWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClick()
    }

    private fun initClick(){
        binding.btnWelcome.setOnClickListener { goToInstructions(it) }
    }

    private fun goToInstructions(mView: View){
        val dirs = WelcomeDirections.actionWelcomeToInstruction()
        mView.findNavController().navigate(dirs)
    }


}