package com.example.cultura

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DodajFavoritaAdapter(
    private val destinacije: List<String>
) : RecyclerView.Adapter<DodajFavoritaAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val naziv: TextView = view.findViewById(R.id.naziv_destinacije)
        val dodajBtn: Button = view.findViewById(R.id.dodaj_gumb)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dodaj_favorita, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val destinacija = destinacije[position]
        holder.naziv.text = destinacija
        holder.dodajBtn.setOnClickListener {
        }
    }

    override fun getItemCount(): Int = destinacije.size
}
