package com.udacity.shoestore.presentation.shoe.shoedetail

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
import com.udacity.shoestore.databinding.ShoeDetailBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetail : Fragment() {

    private lateinit var binding: ShoeDetailBinding
    private val viewModel: AppViewModel by activityViewModels()
    private lateinit var shoe: Shoe

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.shoe_detail, container, false)
        shoe = Shoe(name = "", size = 0.0, company = "", description = "")
        binding.appVM = viewModel
        binding.shoe = shoe
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
        initObservers()

    }

    private fun navigateToShoeList(mView:View){
        val dirs = ShoeDetailDirections.actionShoeDetailToShoeList()
        mView.findNavController().navigate(dirs)
    }

    private fun initObservers(){
        viewModel.actualShoeSize.observe(viewLifecycleOwner,{
            shoe.size = viewModel.convertStringToDouble(it)
        })
    }

    private fun initClickListeners(){
        binding.btnSave.setOnClickListener {
            viewModel.addShoeToList(shoe)
            navigateToShoeList(it)
        }

        binding.btnCancel.setOnClickListener {
            navigateToShoeList(it)
        }
    }



}