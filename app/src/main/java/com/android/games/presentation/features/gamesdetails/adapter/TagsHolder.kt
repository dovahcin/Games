package com.android.games.presentation.features.gamesdetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.games.databinding.ItemTagsBinding
import com.android.games.domain.Tag

class TagsHolder(private val binding: ItemTagsBinding): RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup) =
            TagsHolder(
                ItemTagsBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }

    fun bind(tag: Tag) {
        binding.tag = tag
        binding.executePendingBindings()
    }
}