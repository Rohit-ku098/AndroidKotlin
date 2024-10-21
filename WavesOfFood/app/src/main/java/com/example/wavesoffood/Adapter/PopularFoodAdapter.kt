package com.example.wavesoffood.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wavesoffood.R


class PopularFoodAdapter(
    val itemsName: ArrayList<String>,
    val itemsImage: ArrayList<Int>,
    val itemsPrice: ArrayList<String>
): RecyclerView.Adapter<PopularFoodAdapter.PopularFoodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularFoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_food_item, parent, false)
        return PopularFoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularFoodViewHolder, position: Int) {
        holder.itemName.text = itemsName[position]
        holder.itemImage.setImageResource(itemsImage[position])
        holder.itemPrice.text = itemsPrice[position]
    }

    override fun getItemCount(): Int {
        return itemsName.size
    }

    class PopularFoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImage = view.findViewById<ImageView>(R.id.itemImage)
        val itemName = view.findViewById<TextView>(R.id.itemName)
        val itemPrice = view.findViewById<TextView>(R.id.itemPrice)
    }
}