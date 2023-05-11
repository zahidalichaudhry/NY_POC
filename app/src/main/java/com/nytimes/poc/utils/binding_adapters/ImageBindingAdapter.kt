package com.nytimes.poc.utils.binding_adapters

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageBindingAdapter {



    @JvmStatic
    @BindingAdapter(value = ["loadImageFromURL", "placeHolder"], requireAll = false)
    fun loadImageFromURL(
        view: ImageView,
        imageUrl: String?,
        placeHolder: Drawable?
    ) {
        if (imageUrl.isNullOrEmpty()) {
            Glide.with(view.context)
                .load(placeHolder)
                .fitCenter()
                .into(view)
        } else {
            Glide.with(view.context)
                .load(imageUrl)
                .placeholder(placeHolder)
                .error(placeHolder)
                .centerCrop()
                .into(view)
        }
    }


}