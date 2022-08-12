package com.android.games.presentation.features.search.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.games.domain.Result

class SearchResultsAdapter(
    private val itemClick: (Int, String) -> Unit,
    private var searchQuery: String = "",
    private var items: MutableList<Result> = mutableListOf()
) : RecyclerView.Adapter<SearchResultsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsHolder =
        SearchResultsHolder.create(parent)

    override fun onBindViewHolder(holder: SearchResultsHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            itemClick.invoke(items[position].id, searchQuery)
        }
    }

    override fun getItemCount(): Int = items.count()

    fun update(items: MutableList<Result>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun update(searchQuery: String) {
        this.searchQuery = searchQuery
    }
}