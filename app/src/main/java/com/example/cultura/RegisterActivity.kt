package com.example.cultura

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        db = AppDatabase.getDatabase(this)

        val emailInput = findViewById<EditText>(R.id.register_email)
        val passwordInput = findViewById<EditText>(R.id.register_password)
        val confirmInput = findViewById<EditText>(R.id.register_confirm_password)
        val registerButton = findViewById<Button>(R.id.register_button)
        val loginLink = findViewById<TextView>(R.id.login_link)

        registerButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val confirmPassword = confirmInput.text.toString()

            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Popunite sva polja", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Zaporke se ne podudaraju", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val existing = withContext(Dispatchers.IO) {
                    db.userDao().getUserByEmail(email)
                }

                if (existing != null) {
                    Toast.makeText(this@RegisterActivity, "Korisnik već postoji", Toast.LENGTH_SHORT).show()
                } else {
                    withContext(Dispatchers.IO) {
                        db.userDao().insertUser(User(email, password))
                    }

                    // ✅ Spremi email nakon uspješne registracije
                    getSharedPreferences("CulturaPrefs", MODE_PRIVATE)
                        .edit()
                        .putString("user_email", email)
                        .apply()

                    Toast.makeText(this@RegisterActivity, "Registracija uspješna", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                    finish()
                }
            }
        }

        loginLink.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
