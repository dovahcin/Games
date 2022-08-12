package com.android.games.presentation.features.search.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.games.domain.SearchHistory

class SearchHistoryAdapter(
    private val deleteClick: (Int) -> Unit,
    private val historyClick: (String) -> Unit,
    private var items: MutableList<SearchHistory> = mutableListOf()
) : RecyclerView.Adapter<SearchHistoryHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryHolder =
        SearchHistoryHolder.create(parent)

    override fun onBindViewHolder(holder: SearchHistoryHolder, position: Int) {
        holder.bind(items[position])
        holder.binding.imageCross.setOnClickListener {
            deleteClick.invoke(items[position].id!!)
        }
        holder.binding.gameTitle.setOnClickListener {
            historyClick.invoke(holder.binding.gameTitle.text.toString())
        }
    }

    override fun getItemCount(): Int = items.count()

    fun update(items: MutableList<SearchHistory>) {
        this.items.clear()
        this.items = items
        notifyDataSetChanged()
    }
}