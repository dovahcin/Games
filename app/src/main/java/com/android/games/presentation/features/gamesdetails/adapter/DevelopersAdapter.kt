package com.android.games.presentation.features.gamesdetails.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.games.domain.Developer

class DevelopersAdapter(private val items: MutableList<Developer> = mutableListOf()) : RecyclerView.Adapter<DevelopersHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevelopersHolder =
        DevelopersHolder.create(parent)


    override fun onBindViewHolder(holder: DevelopersHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun update(developers: MutableList<Developer>) {
        items.addAll(developers)
        notifyItemRangeInserted(itemCount, developers.size)
    }

}