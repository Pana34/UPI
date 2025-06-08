package com.example.cultura

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.activity.enableEdgeToEdge

    class PostavkeActivity : AppCompatActivity() {

    private val PREFS_NAME = "Postavke"
    private val KEY_JEZIK = "jezik"
    private val KEY_NOTIF = "notifikacije"
    private val KEY_ZVUK = "zvuk"
    private val KEY_VIBRA = "vibracija"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_postavke)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.postavke_root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        val spinner = findViewById<Spinner>(R.id.spinnerJezik)
        val switchNotif = findViewById<Switch>(R.id.switchNotifikacije)
        val switchZvuk = findViewById<Switch>(R.id.switchZvuk)
        val switchVibra = findViewById<Switch>(R.id.switchVibracija)
        val gumbOdjava = findViewById<Button>(R.id.gumbOdjava)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        val jezici = arrayOf("Hrvatski", "Engleski", "Njemački", "Talijanski")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, jezici)
        spinner.adapter = adapter

        spinner.setSelection(jezici.indexOf(prefs.getString(KEY_JEZIK, "Hrvatski")))
        switchNotif.isChecked = prefs.getBoolean(KEY_NOTIF, true)
        switchZvuk.isChecked = prefs.getBoolean(KEY_ZVUK, true)
        switchVibra.isChecked = prefs.getBoolean(KEY_VIBRA, true)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View, pos: Int, id: Long) {
                prefs.edit().putString(KEY_JEZIK, jezici[pos]).apply()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        switchNotif.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean(KEY_NOTIF, isChecked).apply()
        }

        switchZvuk.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean(KEY_ZVUK, isChecked).apply()
        }

        switchVibra.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean(KEY_VIBRA, isChecked).apply()
        }

        gumbOdjava.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Odjava")
                .setMessage("Jeste li sigurni da se želite odjaviti?")
                .setPositiveButton("Da") { _, _ ->
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Ne", null)
                .show()
        }

        bottomNavigationView.selectedItemId = R.id.nav_postavke

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_pocetna -> {
                    val intent = Intent(this, GlavnaStranica::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.nav_profil -> {
                    val intent = Intent(this, ProfilActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.nav_postavke -> true
                else -> false
            }
        }
    }
}
