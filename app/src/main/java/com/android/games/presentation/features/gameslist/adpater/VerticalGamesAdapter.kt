package com.android.games.presentation.features.gameslist.adpater

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.games.domain.Result

class VerticalGamesAdapter(
    private val itemClick: ItemClick,
    private var items: MutableList<Result> = mutableListOf(),
) : RecyclerView.Adapter<VerticalGamesHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalGamesHolder =
        VerticalGamesHolder.create(parent)


    override fun onBindViewHolder(holder: VerticalGamesHolder, position: Int) {
        val genreAdapter = GenresAdapter()

        holder.bind(items[position])
        holder.binding.recyclerGenre.adapter = genreAdapter
        genreAdapter.update(items[position].genres)
        holder.itemView.setOnClickListener {
            itemClick.onItemClicked(items[position].id)
        }
    }

    override fun getItemCount(): Int = items.size

    fun update(games: MutableList<Result>) {
        if (!this.items.containsAll(games))
            this.items.addAll(games)

        val startItem = itemCount
        notifyItemRangeInserted(startItem, games.size)
    }

    interface ItemClick {
        fun onItemClicked(gameId: Int)
    }
}

