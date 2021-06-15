package com.ardhacodes.subs1_jetpack.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object Helper {
    const val EXTRA_MOVIE = "MOVIE"
    const val EXTRA_TV_SHOW = "TV_SHOW"
    const val IMAGE_URL_API = "https://image.tmdb.org/t/p/"
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val TYPE_MOVIE = "TYPE_MOVIE"
    const val TYPE_TVSHOW = "TYPE_TVSHOW"
    const val POSTER_SIZE_W185 = "w185"
    const val POSTER_SIZE_W780 = "w780"
    const val KEY_API = "57d7a07963575b131d0f873f638ec911"


    fun setImageGlide(context: Context, imagePath: String, imageView: ImageView){
        Glide.with(context).clear(imageView)
        Glide.with(context).load(imagePath).into(imageView)
    }
}