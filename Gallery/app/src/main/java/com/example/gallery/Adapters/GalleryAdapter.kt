package com.example.gallery.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gallery.Model.MediaSection
import com.example.gallery.R
import com.example.gallery.Utils.formatDate
import com.qtalk.recyclerviewfastscroller.RecyclerViewFastScroller
import java.util.Date

class GalleryAdapter(val context:Context,val mediaList: List<MediaSection>): RecyclerView.Adapter<RecyclerView.ViewHolder>(), RecyclerViewFastScroller.OnPopupTextUpdate {
    private var itemWidth: Int = context.resources.getDimensionPixelSize(R.dimen.item_width)
    companion object {
        private val VIEW_TYPE_DATE_HEADER = 0
        private val VIEW_TYPE_MEDIA = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (mediaList[position]) {
            is MediaSection.DateHeader -> VIEW_TYPE_DATE_HEADER
            else -> VIEW_TYPE_MEDIA
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
            VIEW_TYPE_DATE_HEADER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gallery_date_layout, parent, false)
                return DateHeaderViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gallery_media, parent, false)
                return MediaViewHolder(view)
            }
        }
    }
    
    override fun getItemCount(): Int = mediaList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(mediaList[position]) {
            is MediaSection.DateHeader -> (holder as DateHeaderViewHolder).bind(mediaList[position] as MediaSection.DateHeader)
            else -> (holder as MediaViewHolder).bind(context, mediaList[position] as MediaSection.Media, itemWidth)
        }
    }

    class MediaViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val gridImageItem = view.findViewById<ImageView>(R.id.gridImageItem)
        val videoDuration = view.findViewById<TextView>(R.id.gridItemVideoDuration)

        fun bind(context: Context, media: MediaSection.Media, itemWidth: Int) {
            Glide.with(context).load(media.uri).into(gridImageItem)
            if (media.isVideo) {
                videoDuration.visibility = View.VISIBLE
                videoDuration.text = timeString(media.duration)
            } else {
                videoDuration.visibility = View.GONE
            }

            // Dynamically set the width and height of each item based on itemWidth
            val layoutParams = gridImageItem.layoutParams
            layoutParams.width = itemWidth
            layoutParams.height = itemWidth
            gridImageItem.layoutParams = layoutParams
        }

        // Method to convert duration to time string
        private fun timeString(duration: Long): CharSequence? {
            val seconds = duration / 1000
            val minutes = seconds / 60
            val hours = minutes / 60
            return if (hours > 0) {
                hours.toString() + ":" + (minutes % 60).toString().padStart(2, '0') + ":" + (seconds % 60).toString().padStart(2, '0')
            } else {
                minutes.toString().padStart(1, '0') + ":" + (seconds % 60).toString().padStart(2, '0')
            }
        }
    }

    class DateHeaderViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val dateHeader = view.findViewById<TextView>(R.id.itemDate)
        fun bind(dateHeader: MediaSection.DateHeader) {
            this.dateHeader.text = dateHeader.date
        }
    }

    // Method to update item width dynamically
    fun updateItemWidth(newWidth: Int) {
        itemWidth = newWidth
        notifyDataSetChanged() // Refresh the adapter
    }

    override fun onChange(position: Int): CharSequence {
        if(mediaList[position] is MediaSection.DateHeader) {
             return (mediaList[position] as MediaSection.DateHeader).date
        }
        else {
            return formatDate((mediaList[position] as MediaSection.Media).dateAdded*1000 )
        }

    }
}