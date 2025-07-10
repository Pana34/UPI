package com.example.cultura

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerLink: TextView
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = AppDatabase.getDatabase(this)

        emailEditText = findViewById(R.id.login_email)
        passwordEditText = findViewById(R.id.login_password)
        loginButton = findViewById(R.id.prijavagumb)
        registerLink = findViewById(R.id.register_link)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Unesite email i zaporku", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val user = withContext(Dispatchers.IO) {
                    db.userDao().getUserByEmail(email)
                }

                if (user != null && user.password == password) {
                    // 🔐 Spremi korisnički e-mail u SharedPreferences
                    getSharedPreferences("CulturaPrefs", MODE_PRIVATE)
                        .edit()
                        .putString("user_email", email)
                        .apply()

                    Toast.makeText(this@MainActivity, "Dobrodošli!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@MainActivity, GlavnaStranica::class.java))
                    finish()
                } else {
                    Toast.makeText(this@MainActivity, "Neispravni podaci", Toast.LENGTH_SHORT).show()
                }
            }
        }

        registerLink.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
