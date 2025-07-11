package com.example.cultura

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import android.widget.LinearLayout

class GlavnaStranica : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_glavna_stranica)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.nav_pocetna

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_pocetna -> true
                R.id.nav_profil -> {
                    startActivity(Intent(this, ProfilActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_postavke -> {
                    startActivity(Intent(this, PostavkeActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }

        val db = AppDatabase.getDatabase(this)

        lifecycleScope.launch {
            if (db.destinacijaDao().getSveDestinacije().isEmpty()) {
                val grcka = Destinacija(
                    naziv = "Grčka",
                    opis = "Grčka je poznata po drevnoj povijesti, hramovima i bogatoj mitologiji. " +
                            "Osim Atene i otoka, Grčka je i dom izvrsne kuhinje, plave arhitekture i srdačnih ljudi.",
                    slikaIme = "grcka"
                )

                val spanjolska = Destinacija(
                    naziv = "Španjolska",
                    opis = "Španjolska kombinira sunčane plaže, bogatu povijest i živopisnu kulturu. " +
                            "Poznata je po flamenku, tapasu i arhitekturi Antonija Gaudija.",
                    slikaIme = "spanjolska"
                )

                val portugal = Destinacija(
                    naziv = "Portugal",
                    opis = "Portugal oduševljava fado glazbom, povijesnim gradovima i plavim pločicama azulejos. " +
                            "Idealna je destinacija za istraživanje obale, vina i lokalne kulture.",
                    slikaIme = "portugal"
                )

                db.destinacijaDao().insert(grcka)
                db.destinacijaDao().insert(spanjolska)
                db.destinacijaDao().insert(portugal)

                db.obicajDao().insertAll(
                    Obicaj(0, 1, "Zajedničko objedovanje uz mezze."),
                    Obicaj(0, 2, "Siesta – popodnevni odmor."),
                    Obicaj(0, 3, "Fado glazba kao izraz emocije i kulture.")
                )

                db.frazaDao().insertAll(
                    Fraza(0, 1, "Kalimera", "Dobro jutro"),
                    Fraza(0, 1, "Kalispera", "Dobra večer"),
                    Fraza(0, 1, "Yassas", "Dobar dan / Pozdrav"),
                    Fraza(0, 2, "Buenos días", "Dobro jutro"),
                    Fraza(0, 2, "Buenas tardes", "Dobar dan"),
                    Fraza(0, 2, "Buenas noches", "Dobra večer"),
                    Fraza(0, 3, "Bom dia", "Dobro jutro"),
                    Fraza(0, 3, "Boa tarde", "Dobar dan"),
                    Fraza(0, 3, "Boa noite", "Dobra večer")
                )
            }
        }

        val slikaGrcka = findViewById<ImageView>(R.id.slikaGrcka)
        val slikaSpanjolska = findViewById<ImageView>(R.id.slikaSpanjolska)
        val slikaPortugal = findViewById<ImageView>(R.id.slikaPortugal)

        slikaGrcka.setOnClickListener {
            val intent = Intent(this, DetaljiDestinacijeActivity::class.java)
            intent.putExtra("destinacijaId", 1)
            startActivity(intent)
        }

        slikaSpanjolska.setOnClickListener {
            val intent = Intent(this, DetaljiDestinacijeActivity::class.java)
            intent.putExtra("destinacijaId", 2)
            startActivity(intent)
        }

        slikaPortugal.setOnClickListener {
            val intent = Intent(this, DetaljiDestinacijeActivity::class.java)
            intent.putExtra("destinacijaId", 3)
            startActivity(intent)
        }

        // 🔁 NOVO: Otvaranje Favorita
        val favoritiButton = findViewById<LinearLayout>(R.id.favoritiButton)
        favoritiButton.setOnClickListener {
            val intent = Intent(this, FavoritiActivity::class.java)
            startActivity(intent)
        }
    }
}
