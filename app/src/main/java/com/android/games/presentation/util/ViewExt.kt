package com.android.games.presentation

import android.widget.ImageView
import com.android.games.R
import com.squareup.picasso.Picasso

fun ImageView.loadImage(uri: String) {
    Picasso.get()
        .load(uri)
        .placeholder(R.drawable.ic_baseline_image_24)
        .fit()
        .centerCrop()
        .into(this)
}