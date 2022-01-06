package com.udacity.shoestore.presentation.shoe.shoelist

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.setMargins
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.google.android.material.card.MaterialCardView
import com.udacity.shoestore.AppViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeListBinding
import timber.log.Timber

class ShoeList : Fragment() {

    private lateinit var binding:ShoeListBinding
    private val viewModel: AppViewModel by activityViewModels()
    private val mCtx : FragmentActivity by lazy {
        requireActivity()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.shoe_list,container, false)
        initObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.btnAddShoe.setOnClickListener { navigateToDetail(it) }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        navigateToLogin(binding.root)
        return true
    }

    private fun navigateToLogin(mView:View){
        val loginDirections = ShoeListDirections.actionShoeListToLogin()
        mView.findNavController().navigate(loginDirections)
    }

    private fun navigateToDetail(mView: View){
        val detailDirections = ShoeListDirections.actionShoeListToShoeDetail()
        mView.findNavController().navigate(detailDirections)
    }

    private fun initObservers(){
        viewModel.shoeData.observe(viewLifecycleOwner, {
            Timber.d("SIZE: ${it.size}")
            binding.shoeList.removeAllViews()
            it.forEach { shoe ->
                binding.shoeList.addView(
                    createShoeCard(
                        shoe.name, shoe.company, shoe.size, shoe.description
                    )
                )
            }
        })
    }

    private fun createShoeCard(name:String, company:String, size:Double, desc:String) : MaterialCardView{

        //create card view
        val cardView = MaterialCardView(mCtx)
        //card properties
        cardView.cardElevation = mCtx.resources.getDimension(R.dimen.cardview_default_elevation)
        setUpMargins(cardView, 10)

        //linear layout
        val layout = LinearLayout(mCtx)
        layout.orientation = LinearLayout.VERTICAL
        layout.addView(createTextView(name))
        layout.addView(createTextView(company))
        layout.addView(createTextView(size.toString()))
        layout.addView(createTextView(desc))
        setUpMargins(layout, 5)

        cardView.addView(layout)
        return cardView

    }

    private fun setUpMargins(mView: View, marginSize:Int){
        if(mView.layoutParams is ViewGroup.MarginLayoutParams){
            val params = mView.layoutParams as ViewGroup.MarginLayoutParams
            params.setMargins(marginSize)
            mView.requestLayout()
        }
    }

    private fun createTextView(info:String) : TextView{
        val tView = TextView(mCtx)
        tView.text = info
        setUpMargins(tView, 5)
        return tView
    }

}