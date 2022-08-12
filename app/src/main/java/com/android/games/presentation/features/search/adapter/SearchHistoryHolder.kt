package com.android.games.presentation.features.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.games.databinding.ItemSearchhistoriesBinding
import com.android.games.domain.SearchHistory

class SearchHistoryHolder(val binding: ItemSearchhistoriesBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup) =
            SearchHistoryHolder(
                ItemSearchhistoriesBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }

    fun bind(history: SearchHistory) {
        binding.history = history
        binding.executePendingBindings()
    }
}