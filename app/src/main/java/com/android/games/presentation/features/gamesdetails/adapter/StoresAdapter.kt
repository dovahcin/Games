package com.android.games.presentation.features.gamesdetails.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.games.domain.Store
import com.android.games.domain.StoreX

class StoresAdapter(private val items: MutableList<Store> = mutableListOf()) : RecyclerView.Adapter<StoresHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoresHolder =
        StoresHolder.create(parent)

    override fun onBindViewHolder(holder: StoresHolder, position: Int) {
        holder.bind(items[position].store)
    }

    override fun getItemCount(): Int = items.size

    fun update(stores: MutableList<Store>) {
        items.addAll(stores)
        notifyItemRangeInserted(itemCount, stores.size)
    }
}