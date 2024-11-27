package com.example.bikecyclerentalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var logoutBtn: Button
    private lateinit var addNewBicycle: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logoutBtn = findViewById(R.id.logoutBtn)
        addNewBicycle = findViewById(R.id.addNewBicycle)

        addNewBicycle.setOnClickListener {
            startActivity(Intent(this@MainActivity, InsertionActivity::class.java))
        }

        logoutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }
    }
}