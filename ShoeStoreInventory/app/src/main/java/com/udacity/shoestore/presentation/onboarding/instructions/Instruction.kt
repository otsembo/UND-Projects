package com.udacity.shoestore.presentation.onboarding.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.udacity.shoestore.databinding.OnboardingInstructionBinding

class Instruction : Fragment() {

    private lateinit var binding:OnboardingInstructionBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = OnboardingInstructionBinding.inflate(inflater, container, false)
        return binding.root
    }

}