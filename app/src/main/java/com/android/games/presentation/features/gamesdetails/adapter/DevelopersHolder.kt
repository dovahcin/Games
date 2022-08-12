package com.android.games.presentation.features.gamesdetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.games.databinding.ItemDeveloperBinding
import com.android.games.domain.Developer

class DevelopersHolder(private val binding: ItemDeveloperBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup) =
            DevelopersHolder(
                ItemDeveloperBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }

    fun bind(developer: Developer) {
        binding.developer = developer
        binding.executePendingBindings()
    }
}