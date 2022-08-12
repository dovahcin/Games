package com.android.games.presentation.features.gamesdetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.games.databinding.ItemPlatformsBinding
import com.android.games.domain.PlatformXX

class PlatformsHolder(private val binding: ItemPlatformsBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup) =
            PlatformsHolder(
                ItemPlatformsBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }

    fun bind(platform: PlatformXX) {
        binding.platform = platform
        binding.executePendingBindings()
    }
}