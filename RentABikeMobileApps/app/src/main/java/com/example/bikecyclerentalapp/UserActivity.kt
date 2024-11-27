package com.example.bikecyclerentalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class UserActivity : AppCompatActivity() {
        private lateinit var logoutBtn: Button
        private lateinit var search: Button
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_user)

            logoutBtn = findViewById(R.id.logoutBtn)
            search = findViewById(R.id.search)

            search.setOnClickListener {
                startActivity(Intent(this@UserActivity, FetchingActivity::class.java))
            }
            logoutBtn.setOnClickListener {
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this@UserActivity, LoginActivity::class.java))
                finish()
            }
    }
}