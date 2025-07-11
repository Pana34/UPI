package com.example.cultura

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavoritiActivity : AppCompatActivity() {

    private lateinit var listaFavorita: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoriti)

        // 🌍 Lista omiljenih destinacija
        val favoriti = listOf("Grčka", "Španjolska", "Portugal")

        listaFavorita = findViewById(R.id.listaFavorita)
        listaFavorita.layoutManager = LinearLayoutManager(this)
        listaFavorita.adapter = FavoritiAdapter(favoriti) { naziv ->
            val intent = Intent(this, DetaljiDestinacijeActivity::class.java)

            // 🧭 Mapiraj naziv u ID
            val destinacijaId = when (naziv) {
                "Grčka" -> 1
                "Španjolska" -> 2
                "Portugal" -> 3
                else -> -1 // ili neka default destinacija
            }

            if (destinacijaId != -1) {
                intent.putExtra("destinacijaId", destinacijaId)
                startActivity(intent)
            }
        }

        // ➕ Dodaj favorita gumb
        val dodajGumb = findViewById<com.google.android.material.button.MaterialButton>(R.id.gumbDodajFavorita)
        dodajGumb.setOnClickListener {
            startActivity(Intent(this, DodajFavoritaActivity::class.java))
        }

        // 🔙 Povratak gumb
        val gumbNatrag = findViewById<Button>(R.id.gumbNatrag)
        gumbNatrag.setOnClickListener {
            finish()
        }
    }
}
