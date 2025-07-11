package com.example.cultura

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

data class Pitanje(
    val tekst: String,
    val opcije: List<String>,
    val tocanOdgovor: String
)

class KvizActivity : AppCompatActivity() {

    private lateinit var pitanjeView: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var sljedeciButton: Button

    private var pitanja: List<Pitanje> = listOf()
    private var trenutniIndex = 0
    private var bodovi = 0
    private var destinacijaId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kviz)

        pitanjeView = findViewById(R.id.pitanjeText)
        radioGroup = findViewById(R.id.opcijeGroup)
        sljedeciButton = findViewById(R.id.sljedeciButton)

        destinacijaId = intent.getIntExtra("destinacijaId", 1)
        pitanja = dohvatiPitanjaZaDestinaciju(destinacijaId)

        prikaziPitanje()

        sljedeciButton.setOnClickListener {
            val checkedId = radioGroup.checkedRadioButtonId
            if (checkedId == -1) {
                Toast.makeText(this, "Odaberi odgovor", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val odabrani = findViewById<RadioButton>(checkedId).text.toString()
            if (odabrani == pitanja[trenutniIndex].tocanOdgovor) {
                bodovi++
            }

            trenutniIndex++
            if (trenutniIndex < pitanja.size) {
                prikaziPitanje()
            } else {
                prikaziRezultat()
            }
        }
    }

    private fun prikaziPitanje() {
        val trenutno = pitanja[trenutniIndex]
        pitanjeView.text = trenutno.tekst

        radioGroup.removeAllViews()
        trenutno.opcije.forEach { opcija ->
            val radioButton = RadioButton(this)
            radioButton.text = opcija
            radioButton.tag = "odgovorOpcija"
            radioGroup.addView(radioButton)
        }
    }

    private fun prikaziRezultat() {
        val poruka = "Osvojio si $bodovi/${pitanja.size} bodova."
        val alert = android.app.AlertDialog.Builder(this)
            .setTitle("Rezultat")
            .setMessage(poruka)
            .setPositiveButton("Natrag") { _, _ ->
                finish()
            }
            .create()
        alert.show()
    }

    private fun dohvatiPitanjaZaDestinaciju(destId: Int): List<Pitanje> {
        return when (destId) {
            1 -> listOf(
                Pitanje("Glavni grad Grčke?", listOf("Atena", "Sofija", "Rim", "Beograd"), "Atena"),
                Pitanje("Koje je more uz Grčku?", listOf("Jadransko", "Egejsko", "Baltičko", "Crveno"), "Egejsko"),
                Pitanje("Koji je poznati grčki filozof?", listOf("Platon", "Kant", "Descartes", "Nietzsche"), "Platon"),
                Pitanje("Tradicionalno grčko jelo?", listOf("Tortilla", "Gyros", "Sarma", "Paella"), "Gyros")
            )
            2 -> listOf(
                Pitanje("Glavni grad Španjolske?", listOf("Madrid", "Barcelona", "Lisabon", "Sevilja"), "Madrid"),
                Pitanje("Koji je poznati španjolski ples?", listOf("Flamenko", "Tango", "Samba", "Polka"), "Flamenko"),
                Pitanje("Kojim jezikom govore u Španjolskoj?", listOf("Portugalski", "Španjolski", "Francuski", "Talijanski"), "Španjolski"),
                Pitanje("Španjolski festival rajčica?", listOf("La Tomatina", "Oktoberfest", "Carnaval", "Rio Fiesta"), "La Tomatina")
            )
            3 -> listOf(
                Pitanje("Glavni grad Portugala?", listOf("Lisabon", "Porto", "Madrid", "Braga"), "Lisabon"),
                Pitanje("Koja je rijeka prolazi kroz Lisabon?", listOf("Dunav", "Tejo", "Tajo", "Reno"), "Tejo"),
                Pitanje("Tradicionalna glazba Portugala?", listOf("Fado", "Flamenko", "Bossa nova", "Jazz"), "Fado"),
                Pitanje("Portugal je poznat po?", listOf("Maslinama", "Sushiju", "Vinu Porto", "Vodki"), "Vinu Porto")
            )
            else -> listOf()
        }
    }
}
