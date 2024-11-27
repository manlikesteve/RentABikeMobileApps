package com.example.bikecyclerentalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertionActivity : AppCompatActivity() {
    private lateinit var etmodelName: EditText
    private lateinit var ettype: EditText
    private lateinit var etgears: EditText
    private lateinit var etlocation: EditText
    private lateinit var etrent: EditText
    private lateinit var etcontact: EditText
    private lateinit var etemail: EditText
    private lateinit var btnSave: Button
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)

        etmodelName = findViewById(R.id.etBicycleName)
        ettype = findViewById(R.id.etType)
        etgears = findViewById(R.id.etGears)
        etlocation = findViewById(R.id.etLocation)
        etrent = findViewById(R.id.etRent)
        etcontact = findViewById(R.id.etContact)
        etemail = findViewById(R.id.etEmail)
        btnSave = findViewById(R.id.btnSave)

        dbRef = FirebaseDatabase.getInstance().getReference("Bicycles")
        btnSave.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val name = etmodelName.text.toString()
        val type = ettype.text.toString()
        val gears = etgears.text.toString()
        val location = etlocation.text.toString()
        val rent = etrent.text.toString()
        val contact = etcontact.text.toString()
        val email = etemail.text.toString()
        val id = dbRef.push().key!!

        if(name.isEmpty() || type.isEmpty() || gears.isEmpty() || location.isEmpty() || rent.isEmpty() || contact.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Enter details", Toast.LENGTH_SHORT).show()
        }else{
            val bicycleModel = BicycleModel(id,name,type,gears,location,rent,contact,email)
            dbRef.child(id).setValue(bicycleModel)
                .addOnCompleteListener {
                    Toast.makeText(this, "Bicycle data saved successfully", Toast.LENGTH_SHORT).show()
                    etmodelName.text.clear()
                    ettype.text.clear()
                    etgears.text.clear()
                    etlocation.text.clear()
                    etrent.text.clear()
                    etcontact.text.clear()
                    etemail.text.clear()

                    startActivity(Intent(this@InsertionActivity, MainActivity::class.java))
                }
        }
    }
}