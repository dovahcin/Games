package com.android.games.presentation.features.gamesdetails.adapter

import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.android.games.domain.ScreenShot
import com.android.games.domain.ShortScreenshot

class ScreenShotsAdapter(
    private val imageClick: (String) -> Unit,
    private val items: MutableList<ScreenShot> = mutableListOf()
) : RecyclerView.Adapter<ScreenShotsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenShotsHolder =
        ScreenShotsHolder.create(parent)


    override fun onBindViewHolder(holder: ScreenShotsHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            imageClick.invoke(items[position].image)
        }
    }

    override fun getItemCount(): Int =
        items.size

    fun update(screenShots: MutableList<ScreenShot>) {
        items.clear()
        items.addAll(screenShots)
        notifyItemRangeInserted(itemCount,screenShots.size)
    }
}