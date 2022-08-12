package com.android.games.presentation.features.gamesdetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.games.databinding.ItemStoreBinding
import com.android.games.domain.StoreX

class StoresHolder(private val binding: ItemStoreBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup) =
            StoresHolder(
                ItemStoreBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }


    fun bind(store: StoreX) {
        binding.store = store
        binding.executePendingBindings()
    }
}