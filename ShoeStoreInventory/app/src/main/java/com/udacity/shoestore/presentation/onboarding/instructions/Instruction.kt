package com.udacity.shoestore.presentation.onboarding.instructions

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
import com.udacity.shoestore.databinding.OnboardingInstructionBinding

class Instruction : Fragment() {

    private lateinit var binding:OnboardingInstructionBinding
    private val viewModel: AppViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.onboarding_instruction,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnShoppingGo.setOnClickListener { navigateToHome(it) }
    }

    private fun navigateToHome(mView:View){
        val dirs = InstructionDirections.actionInstructionToShoeList()
        mView.findNavController().navigate(dirs)
    }

}