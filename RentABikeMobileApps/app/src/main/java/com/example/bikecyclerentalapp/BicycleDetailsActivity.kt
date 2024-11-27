package com.example.bikecyclerentalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class BicycleDetailsActivity : AppCompatActivity() {
    private lateinit var name: TextView
    private lateinit var type: TextView
    private lateinit var gears: TextView
    private lateinit var location: TextView
    private lateinit var rent: TextView
    private lateinit var contact: TextView
    private lateinit var email: TextView
    private lateinit var btnBack: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bicycle_details)

        name = findViewById(R.id.tvName)
        type = findViewById(R.id.tvType)
        gears = findViewById(R.id.tvGears)
        location = findViewById(R.id.tvLocation)
        rent = findViewById(R.id.tvRent)
        contact = findViewById(R.id.tvContact)
        email = findViewById(R.id.tvEmail)
        btnBack = findViewById(R.id.btnBack)

        name.text =intent.getStringExtra("name")
        type.text =intent.getStringExtra("type")
        gears.text =intent.getStringExtra("gears")
        location.text =intent.getStringExtra("location")
        rent.text =intent.getStringExtra("rent")
        contact.text =intent.getStringExtra("contact")
        email.text =intent.getStringExtra("email")

        btnBack.setOnClickListener {
            startActivity(Intent(this@BicycleDetailsActivity, FetchingActivity::class.java))
        }
    }
}