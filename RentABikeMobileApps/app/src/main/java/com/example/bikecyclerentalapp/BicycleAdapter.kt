package com.example.bikecyclerentalapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BicycleAdapter (private val bicycleList: ArrayList<BicycleModel>): RecyclerView.Adapter<BicycleAdapter.ViewHolder>() {
    private lateinit var mListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener) {
        mListener = clickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):BicycleAdapter.ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.bicycle_list_item,parent,false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: BicycleAdapter.ViewHolder, position: Int) {
        val currentBicycle = bicycleList[position]
        holder.tvName.text =currentBicycle.name
        holder.tvRole.text=currentBicycle.location
    }

    override fun getItemCount(): Int {
        return bicycleList.size
    }

    class ViewHolder(itemView: View, clickListener:onItemClickListener): RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvRole: TextView = itemView.findViewById(R.id.tvLocation)
        init{
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}