package com.android.games.presentation.features.gameslist.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.games.databinding.ItemGenresBinding
import com.android.games.domain.Genre

class GenresHolder(val binding: ItemGenresBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) =
            GenresHolder(
                ItemGenresBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }

    fun bind(genre: Genre) {
        binding.genre = genre
        binding.executePendingBindings()
    }

}