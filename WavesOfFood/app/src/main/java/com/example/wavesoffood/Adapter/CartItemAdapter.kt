package com.example.wavesoffood.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wavesoffood.R

class CartItemAdapter(
    private val itemsName: ArrayList<String>,
    private val itemsPrice: ArrayList<String>,
    private val itemsQuantity: ArrayList<Int>,
    private val itemsImage: ArrayList<Int>
): RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder>() {

    interface onClickListener {
        fun onClickDelete(position: Int)
        fun onClick(position: Int)
        fun onClickIncrement(position: Int)
        fun onClickDecrement(position: Int)
    }
    lateinit var clickListener: onClickListener

    fun setOnClickListener(onClickListener: onClickListener) {
        this.clickListener = onClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_cart_food_item, parent, false)
        return CartItemViewHolder(view, clickListener)
    }

    override fun getItemCount(): Int {
        return itemsName.size
    }


    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        holder.itemName.text = itemsName[position]
        holder.itemPrice.text = itemsPrice[position]
        holder.itemQuantity.text = itemsQuantity[position].toString()
        holder.itemImage.setImageResource(itemsImage[position])
    }

    class CartItemViewHolder(val view: View, private val clickListener: onClickListener): RecyclerView.ViewHolder(view) {
        val itemImage = view.findViewById<ImageView>(R.id.cartItemImage)
        val itemName = view.findViewById<TextView>(R.id.cartItemName)
        val itemPrice = view.findViewById<TextView>(R.id.cartItemPrice)
        val itemQuantity = view.findViewById<TextView>(R.id.cartItemQuantity)
        val deleteItem = view.findViewById<ImageView>(R.id.deleteFromCartBtn)
        val incrementBtn = view.findViewById<TextView>(R.id.plusBtn)
        val decrementBtn = view.findViewById<TextView>(R.id.minusBtn)

        init {
            itemName.setOnClickListener {
                clickListener.onClick(adapterPosition)
            }
            itemImage.setOnClickListener {
                clickListener.onClick(adapterPosition)
            }
            deleteItem.setOnClickListener {
                clickListener.onClickDelete(adapterPosition)
            }
            incrementBtn.setOnClickListener {
                clickListener.onClickIncrement(adapterPosition)
            }
            decrementBtn.setOnClickListener {
                clickListener.onClickDecrement(adapterPosition)
            }
        }
    }
}