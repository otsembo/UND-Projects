package com.udacity.asteroidradar.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: AsteroidAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        setUpList()

        initObservers()

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun initObservers(){

        viewModel.asteroids.observe(viewLifecycleOwner, {
            viewModel.finishedLoading()
            adapter.setListItems(it)
        })


        viewModel.navigateToAsteroidDetails.observe(viewLifecycleOwner, {
            it?.let { asteroid ->

                this.findNavController().navigate(
                    MainFragmentDirections.actionShowDetail(asteroid)
                )

                viewModel.finishedNavigation()

            }
        })

    }

    private fun setUpList(){
       adapter = AsteroidAdapter(AsteroidListener {
            viewModel.onAsteroidClicked(it)
        })
        binding.asteroidRecycler.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.show_week -> {}
            R.id.show_today -> {}
            R.id.show_saved -> {}

        }

        return true
    }
}
