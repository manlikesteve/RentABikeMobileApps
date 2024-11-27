package com.example.bikecyclerentalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class FetchingActivity : AppCompatActivity() {
    private lateinit var bicycleRecyclerView: RecyclerView
    private lateinit var dbRef: DatabaseReference
    private lateinit var bicycleList: ArrayList<BicycleModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

        bicycleRecyclerView = findViewById(R.id.rvBicycle)
        bicycleRecyclerView.layoutManager = LinearLayoutManager(this)
        bicycleRecyclerView.hasFixedSize()

        bicycleList = arrayListOf<BicycleModel>()

        getBicycleData()
    }

    private fun getBicycleData() {
        dbRef = FirebaseDatabase.getInstance().getReference("Bicycles")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                bicycleList.clear()
                if(snapshot.exists()){
                    for(jobSnap in snapshot.children){
                        val jobData =jobSnap.getValue(BicycleModel::class.java)
                        bicycleList.add(jobData!!)
                    }

                    val mAdapter =BicycleAdapter(bicycleList)
                    bicycleRecyclerView.adapter =mAdapter

                    mAdapter.setOnItemClickListener(object : BicycleAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@FetchingActivity, BicycleDetailsActivity::class.java)
                            intent.putExtra("name",bicycleList[position].name)
                            intent.putExtra("type",bicycleList[position].type)
                            intent.putExtra("gears",bicycleList[position].gears)
                            intent.putExtra("location",bicycleList[position].location)
                            intent.putExtra("rent",bicycleList[position].rent)
                            intent.putExtra("contact",bicycleList[position].contact)
                            intent.putExtra("email",bicycleList[position].email)
                            startActivity(intent)
                        }

                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}