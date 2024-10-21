package com.example.apiapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProductAdapter(private val product: MutableList<Product>):
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val productImage = view.findViewById<ImageView>(R.id.productImage)
        val productTitle = view.findViewById<TextView>(R.id.productTitle)
        val productRating = view.findViewById<TextView>(R.id.productRating)
        val productPrice = view.findViewById<TextView>(R.id.productPrice)
        val ratingImg1 = view.findViewById<ImageView>(R.id.ratingstar1)
        val ratingImg2 = view.findViewById<ImageView>(R.id.ratingstar2)
        val ratingImg3 = view.findViewById<ImageView>(R.id.ratingstar3)
        val ratingImg4 = view.findViewById<ImageView>(R.id.ratingstar4)
        val ratingImg5 = view.findViewById<ImageView>(R.id.ratingstar5)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_card, parent, false)
        return ViewHolder(viewInflater)
    }

    override fun getItemCount(): Int {
        return product.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(product[position].thumbnail).into(holder.productImage)
        holder.productPrice.text = "$${product[position].price}"
        holder.productRating.text = product[position].rating.toString()
        holder.productTitle.text = product[position].title

        val rating = product[position].rating
        holder.ratingImg1.visibility = View.GONE
        holder.ratingImg2.visibility = View.GONE
        holder.ratingImg3.visibility = View.GONE
        holder.ratingImg4.visibility = View.GONE
        holder.ratingImg5.visibility = View.GONE

        if (rating >= 1) {
            holder.ratingImg1.visibility = View.VISIBLE
        }
        if (rating >= 2) {
            holder.ratingImg2.visibility = View.VISIBLE
        }
        if (rating >= 3) {
            holder.ratingImg3.visibility = View.VISIBLE
        }
        if (rating >= 4) {
            holder.ratingImg4.visibility = View.VISIBLE
        }
        if (rating >= 5) {
            holder.ratingImg5.visibility = View.VISIBLE
        }
    }
}