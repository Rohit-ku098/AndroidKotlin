package com.example.mylearningapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class MyRecyclerViewNewsAdapter(private val data: ArrayList<NewsData>) :
RecyclerView.Adapter<MyRecyclerViewNewsAdapter.MyViewHolder>(){

    lateinit var itemClickListner: onItemClickListner
    interface onItemClickListner {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListner(listner: onItemClickListner) {
        itemClickListner = listner
    }

    class MyViewHolder(view: View, listner: onItemClickListner) : RecyclerView.ViewHolder(view) {
        val headingText: TextView
        val headingImage: ShapeableImageView
        init {
            headingText = view.findViewById<TextView>(R.id.headingText)
            headingImage = view.findViewById<ShapeableImageView>(R.id.headingImage)
            view.setOnClickListener {
                listner.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyRecyclerViewNewsAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_recycler_view_news_item, parent, false)

        return MyViewHolder(view, itemClickListner)
    }

    override fun onBindViewHolder(holder: MyRecyclerViewNewsAdapter.MyViewHolder, position: Int) {
        holder.headingText.text = data[position].heading
        holder.headingImage.setImageResource(data[position].image)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}