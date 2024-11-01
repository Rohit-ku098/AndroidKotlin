package com.example.gallery.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gallery.Model.Media
import com.example.gallery.R

class GalleryAdapter(val context:Context,val mediaList: List<Media>): RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gallery_media, parent, false)
        return ViewHolder(context, view)
    }

    override fun getItemCount(): Int = mediaList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val media = mediaList[position]
        Glide.with(context).load(media.uri).into(holder.gridImageItem)
    }

    class ViewHolder(val context: Context, view: View): RecyclerView.ViewHolder(view) {
        val gridImageItem = view.findViewById<ImageView>(R.id.gridImageItem)
        init {
            val dimension = calculateDimension()
            gridImageItem.layoutParams.width = dimension
            gridImageItem.layoutParams.height = dimension
        }

        fun calculateDimension(): Int {
            val screenWidth = context.resources.displayMetrics.widthPixels
            val screenHeight = context.resources.displayMetrics.heightPixels
            val dimension = if (screenWidth < screenHeight) screenWidth / 3 else screenHeight / 3
            return dimension
        }
    }

}