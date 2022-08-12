package com.android.games.presentation.features.gamesdetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.games.databinding.ItemPublisherBinding
import com.android.games.domain.Publisher

class PublishersHolder(private val binding: ItemPublisherBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup) =
            PublishersHolder(
                ItemPublisherBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }

    fun bind(publisher: Publisher) {
        binding.publisher = publisher
        binding.executePendingBindings()
    }
}