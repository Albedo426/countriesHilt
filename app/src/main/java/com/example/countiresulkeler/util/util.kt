package com.example.countiresulkeler.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.countiresulkeler.R

//bunun olayı classlara ekleme yapma örnek aşşağıda
/*
fun String.myextensiton(myParametre:String):String{//litaratüldeki adı extensiton
    print(myParametre)
    return myParametre
}*/
fun ImageView.downloadFromUrl(url:String? ,circularProgressDrawable:CircularProgressDrawable){
    val optionos =RequestOptions()
        .placeholder(circularProgressDrawable)
        //.placeholder(placeHolderProgressBar(context)) test et
        .error(R.mipmap.ic_launcher)//defauld resim
    Glide.with(context)
        .setDefaultRequestOptions(optionos)
        .load(url)
        .into(this)

}
fun placeHolderProgressBar(context:Context):CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth=8f
        centerRadius=40f
    }
}
//xmlde çalışması için
@BindingAdapter("android:downloadUrl")
fun dowloandImage(imageView:ImageView,url:String?){
    imageView.downloadFromUrl(url, placeHolderProgressBar(imageView.context))
}