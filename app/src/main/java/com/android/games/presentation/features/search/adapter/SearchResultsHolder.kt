package com.android.games.presentation.features.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.games.databinding.ItemSearchresultsBinding
import com.android.games.domain.Result
import com.android.games.presentation.loadImage

class SearchResultsHolder(val binding: ItemSearchresultsBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) =
            SearchResultsHolder(
                ItemSearchresultsBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }

    fun bind(result: Result) {
        binding.result = result
        if (result.background_image != null)
        binding.imageView.loadImage(result.background_image)
        binding.executePendingBindings()
    }

}