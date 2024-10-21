package com.example.wavesoffood.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wavesoffood.R

class HistoryFoodAdapter(
    private val historyItemsImage: MutableList<Int>,
    private val historyItemsName: MutableList<String>,
    private val historyItemsPrice: MutableList<String>
): RecyclerView.Adapter<HistoryFoodAdapter.HistoryFoodViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryFoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_history_item, parent, false)
        return HistoryFoodViewHolder(view)
    }

    override fun getItemCount(): Int {
        return historyItemsName.size
    }

    override fun onBindViewHolder(holder: HistoryFoodViewHolder, position: Int) {
        holder.historyItemName.text = historyItemsName[position]
        holder.historyItemImage.setImageResource(historyItemsImage[position])
        holder.historyItemPrice.text = historyItemsPrice[position]
    }

    class HistoryFoodViewHolder(view: View): RecyclerView.ViewHolder(view){
        val historyItemImage = view.findViewById<ImageView>(R.id.recentBuyItemImage)
        val historyItemName = view.findViewById<TextView>(R.id.recentBuyItemName)
        val historyItemPrice = view.findViewById<TextView>(R.id.recentBuyItemPrice)
    }

}