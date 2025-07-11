package com.example.cultura

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DodajFavoritaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dodaj_favorita)

        val destinacije = listOf("Grčka", "Španjolska", "Portugal", "Francuska", "Italija")

        val recyclerView = findViewById<RecyclerView>(R.id.lista_svih_destinacija)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = DodajFavoritaAdapter(destinacije)

        val backBtn = findViewById<ImageButton>(R.id.btn_natrag)
        backBtn.setOnClickListener {
            finish()
        }
    }
}
