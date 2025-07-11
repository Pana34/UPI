package com.example.cultura

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profil)

        val rootLayout = findViewById<android.view.View>(R.id.profil_root)
        ViewCompat.setOnApplyWindowInsetsListener(rootLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.nav_profil

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_pocetna -> {
                    val intent = Intent(this, GlavnaStranica::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.nav_profil -> {
                    true
                }
                R.id.nav_postavke -> {
                    val intent = Intent(this, PostavkeActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                else -> false
            }
        }


        val emailText = findViewById<TextView>(R.id.email_text)
        val logoutButton = findViewById<Button>(R.id.logout_button)

        val email = getSharedPreferences("CulturaPrefs", MODE_PRIVATE)
            .getString("user_email", "Nepoznat")

        emailText.text = "E-mail: $email"

        logoutButton.setOnClickListener {
            getSharedPreferences("CulturaPrefs", MODE_PRIVATE)
                .edit().clear().apply()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
