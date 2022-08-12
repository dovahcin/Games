package com.android.games.presentation.features.gamesdetails.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.games.domain.Publisher

class PublishersAdapter(private val items: MutableList<Publisher> = mutableListOf()) : RecyclerView.Adapter<PublishersHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublishersHolder =
        PublishersHolder.create(parent)

    override fun onBindViewHolder(holder: PublishersHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun update(publishers: MutableList<Publisher>) {
        items.addAll(publishers)
        notifyItemRangeInserted(itemCount, publishers.size)
    }
}