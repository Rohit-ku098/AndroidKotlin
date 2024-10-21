package com.example.wavesoffood.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wavesoffood.R

class MenuAdapter(
    val itemsName: MutableList<String>,
    val itemsImage: MutableList<Int>,
    val itemsPrice: MutableList<String>
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_food_item, parent, false)
        return MenuViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemsName.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.itemName.text = itemsName[position]
        holder.itemImage.setImageResource(itemsImage[position])
        holder.itemPrice.text = itemsPrice[position]
    }

    inner class MenuViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImage = view.findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.itemImage)
        val itemName = view.findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.itemName)
        val itemPrice = view.findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.itemPrice)
    }
}