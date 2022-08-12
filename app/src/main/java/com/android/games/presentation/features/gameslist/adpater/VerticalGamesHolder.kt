package com.android.games.presentation.features.gameslist.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.games.databinding.ItemVerticalGamesBinding
import com.android.games.domain.Result
import com.android.games.presentation.loadImage

class VerticalGamesHolder(val binding: ItemVerticalGamesBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) =
            VerticalGamesHolder(
                ItemVerticalGamesBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }

    fun bind(game: Result) {
        binding.game = game
        binding.imageView.loadImage(game.background_image!!)
        binding.executePendingBindings()
    }

}