package com.example.cultura

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FavoritiAdapter(
    private val favoriti: List<String>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<FavoritiAdapter.FavoritViewHolder>() {

    inner class FavoritViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val naslov: TextView = view.findViewById(R.id.favorit_naslov)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorit, parent, false)
        return FavoritViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoritViewHolder, position: Int) {
        val naziv = favoriti[position]
        holder.naslov.text = naziv
        holder.itemView.setOnClickListener { onClick(naziv) }
    }

    override fun getItemCount() = favoriti.size
}
