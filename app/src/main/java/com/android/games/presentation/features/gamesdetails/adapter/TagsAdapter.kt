package com.android.games.presentation.features.gamesdetails.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.games.domain.Tag

class TagsAdapter(private val items: MutableList<Tag> = mutableListOf()) :RecyclerView.Adapter<TagsHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsHolder =
        TagsHolder.create(parent)

    override fun onBindViewHolder(holder: TagsHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun update(tags: MutableList<Tag>) {
        items.addAll(tags)
        notifyItemRangeInserted(itemCount, tags.size)
    }
}