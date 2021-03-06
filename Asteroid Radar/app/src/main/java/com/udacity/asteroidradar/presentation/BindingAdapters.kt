package com.udacity.asteroidradar.presentation

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.data.model.Asteroid

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}

@BindingAdapter("imageUrl")
fun bindImageUrl(imageView: ImageView, url:String?){
    val mCtx = imageView.context
    Picasso.with(mCtx)
        .load(url)
        .placeholder(AppCompatResources.getDrawable(mCtx,R.drawable.placeholder_picture_of_day))
        .into(imageView)
}

@BindingAdapter("loadingStatus")
fun bindLoadingStatus(progressBar: ProgressBar, isLoading:Boolean?){
    progressBar.visibility = when(isLoading){
        true ->  View.VISIBLE
        false -> View.GONE
        else ->  View.GONE
    }
}

@BindingAdapter("potentialHazard")
fun bindHazardPotential(imageView: ImageView, isPotentiallyHazardous:Boolean){
    val mCtx = imageView.context
    imageView.contentDescription = when(isPotentiallyHazardous){
        true -> {mCtx.getString(R.string.potentially_hazardous_asteroid_image)}
        false -> {mCtx.getString(R.string.not_hazardous_asteroid_image)}
    }

}











