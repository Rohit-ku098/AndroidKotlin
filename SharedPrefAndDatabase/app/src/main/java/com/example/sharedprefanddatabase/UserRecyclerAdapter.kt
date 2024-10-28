package com.example.sharedprefanddatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sharedprefanddatabase.database.UserEntity

class UserRecyclerAdapter(val userList: MutableList<UserEntity>): RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>() {
    private lateinit var clicklistener: OnItemClickListener
    interface OnItemClickListener {
        fun onDeleteClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        clicklistener = listener
    }
    class ViewHolder(view: View, listener: OnItemClickListener): RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.item_name)
        val email = view.findViewById<TextView>(R.id.item_email)
        val deleteButton = view.findViewById<ImageView>(R.id.deleteButton)
        val date = view.findViewById<TextView>(R.id.item_date)

        init {
            deleteButton.setOnClickListener {
                listener.onDeleteClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view, clicklistener)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = userList[position].name
        holder.email.text = userList[position].email
        holder.date.text = userList[position].date.toString()
    }

}