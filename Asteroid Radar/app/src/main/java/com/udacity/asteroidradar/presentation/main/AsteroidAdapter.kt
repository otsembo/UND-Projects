package com.udacity.asteroidradar.presentation.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.data.model.Asteroid
import com.udacity.asteroidradar.databinding.AsteroidRowBinding

class AsteroidAdapter(private val clickListener : AsteroidListener) :
        ListAdapter<Asteroid, AsteroidViewHolder>(AsteroidAdapterDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        return AsteroidViewHolder.createFrom(parent)
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val asteroid = getItem(position) as Asteroid
        holder.bindData(asteroid, clickListener)
    }

    fun setListItems(list: List<Asteroid>){
        submitList(list)
    }

}

//view holder
class AsteroidViewHolder
    private constructor(private val binding: AsteroidRowBinding) : RecyclerView.ViewHolder(binding.root){

    fun bindData(asteroid: Asteroid, listener:AsteroidListener){
        binding.asteroid = asteroid
        binding.clickListener = listener
        binding.executePendingBindings()
    }

    companion object{
        fun createFrom(container:ViewGroup) : AsteroidViewHolder{
            val binding = AsteroidRowBinding.inflate( LayoutInflater.from(container.context), container, false)
            return AsteroidViewHolder(binding)
        }
    }

}

//diff util callback
class AsteroidAdapterDiffCallback : DiffUtil.ItemCallback<Asteroid>(){
    override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem == newItem
    }

}

//click listener
class AsteroidListener(val clickListener : (asteroid: Asteroid) -> Unit){
    fun onClick(asteroid: Asteroid) = clickListener(asteroid)
}