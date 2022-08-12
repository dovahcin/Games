package com.android.games.presentation.features.gamesdetails.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.games.domain.Platform
import com.android.games.domain.PlatformX
import com.android.games.domain.PlatformXX

class PlatformsAdapter(private val items: MutableList<PlatformX> = mutableListOf()) : RecyclerView.Adapter<PlatformsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatformsHolder =
        PlatformsHolder.create(parent)

    override fun onBindViewHolder(holder: PlatformsHolder, position: Int) {
        holder.bind(items[position].platform)
    }

    override fun getItemCount(): Int = items.size

    fun update(platforms: MutableList<PlatformX>) {
        items.addAll(platforms)
        notifyItemRangeInserted(itemCount, platforms.size)
    }
}