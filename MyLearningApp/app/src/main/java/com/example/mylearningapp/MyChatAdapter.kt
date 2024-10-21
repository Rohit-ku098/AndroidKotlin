package com.example.mylearningapp

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView

class MyChatAdapter(private val context: Activity, private val data: ArrayList<ChatUser>) : ArrayAdapter<ChatUser>(context, R.layout.custom_list_view_chat, data){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.custom_list_view_chat, null)

        val name = view.findViewById<TextView>(R.id.name)
        val image = view.findViewById<CircleImageView>(R.id.profile)
        val message = view.findViewById<TextView>(R.id.message)
        val time = view.findViewById<TextView>(R.id.msgTime)

        name.text = data[position].name
        image.setImageResource(data[position].image)
        message.text = data[position].message
        time.text = data[position].msgTime


        return view
    }
}