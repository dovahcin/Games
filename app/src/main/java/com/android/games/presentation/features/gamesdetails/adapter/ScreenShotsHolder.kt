package com.android.games.presentation.features.gamesdetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.games.databinding.ItemScreenshotBinding
import com.android.games.domain.ScreenShot
import com.android.games.domain.ShortScreenshot
import com.android.games.presentation.loadImage

class ScreenShotsHolder(val binding: ItemScreenshotBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): ScreenShotsHolder {
            return ScreenShotsHolder(
                ItemScreenshotBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }

    fun bind(screenshot: ScreenShot) {
        binding.imageScreenShot.loadImage(screenshot.image)
        binding.executePendingBindings()
    }

}