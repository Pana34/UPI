package com.example.cultura

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class DetaljiDestinacijeActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalji_destinacije)

        db = AppDatabase.getDatabase(this)

        val destinacijaId = intent.getIntExtra("destinacijaId", -1)

        if (destinacijaId != -1) {
            prikaziDetalje(destinacijaId)
        }

        val gumbKviz = findViewById<Button>(R.id.gumbKviz)
        gumbKviz.setOnClickListener {
            val intent = Intent(this, KvizActivity::class.java)
            intent.putExtra("destinacijaId", destinacijaId)
            startActivity(intent)
        }
        val gumbNatrag = findViewById<Button>(R.id.gumbNatrag)
        gumbNatrag.setOnClickListener {
            finish()
        }

    }

    private fun prikaziDetalje(id: Int) {
        val imageView  = findViewById<ImageView>(R.id.imageDestinacija)
        val nazivView  = findViewById<TextView>(R.id.nazivDestinacije)
        val opisView   = findViewById<TextView>(R.id.opisDestinacije)
        val obicajiView= findViewById<TextView>(R.id.obicajiText)
        val frazeView  = findViewById<TextView>(R.id.frazeText)

        lifecycleScope.launch {
            val dest   = db.destinacijaDao().getDestinacijaPoId(id)
            val obicaj = db.obicajDao().getObicajiZaDestinaciju(id)
            val fraze  = db.frazaDao().getFrazeZaDestinaciju(id)

            runOnUiThread {
                val resId = resources.getIdentifier(dest.slikaIme, "drawable", packageName)
                imageView.setImageResource(
                    if (resId != 0) resId else R.drawable.logo
                )
                nazivView.text = dest.naziv
                opisView.text  = dest.opis
                obicajiView.text = obicaj.joinToString("\n") { "• ${it.tekst}" }
                frazeView.text   = fraze.joinToString("\n")  { "• ${it.izgovor} - ${it.prijevod}" }
            }
        }
    }
}
