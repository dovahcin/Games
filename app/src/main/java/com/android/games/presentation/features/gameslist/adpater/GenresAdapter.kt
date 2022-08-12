package com.android.games.presentation.features.gameslist.adpater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.games.domain.Genre

class GenresAdapter(private var items: MutableList<Genre> = mutableListOf()) : RecyclerView.Adapter<GenresHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresHolder =
        GenresHolder.create(parent)

    override fun onBindViewHolder(holder: GenresHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun update(genres: MutableList<Genre>) {
        this.items.addAll(genres)
        notifyItemRangeInserted(itemCount, genres.size)
    }
}