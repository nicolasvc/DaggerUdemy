package com.example.daggerudemy.screens.common.imageloader

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import javax.inject.Inject

class ImageLoader @Inject constructor(private val activity: AppCompatActivity) {

    private val requestOptions = RequestOptions().centerCrop()

    fun loadImagen(imageUrl:String,target:ImageView){
        Glide.with(activity).load(imageUrl).apply(requestOptions).into(target)
    }

}