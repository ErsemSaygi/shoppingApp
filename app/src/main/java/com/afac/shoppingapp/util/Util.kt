package com.afac.shoppingapp.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.afac.shoppingapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions



@BindingAdapter("android:downloadUrl")
fun downloadImage(view:ImageView,url: String?){
    if (url != null) {
        view.downloadFormUrl(url, placeholderProgressBar(view.context))
    };
}
fun placeholderProgressBar(contex: Context):CircularProgressDrawable{

    return CircularProgressDrawable(contex).apply {
        strokeWidth=8f
        centerRadius=40f
        start()
    }
}
fun ImageView.downloadFormUrl(url:String,progressDrawable: CircularProgressDrawable){
    val options= RequestOptions().placeholder(progressDrawable).error(R.mipmap.ic_launcher_round)
    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
}
