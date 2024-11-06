package com.example.gallery.Adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gallery.Fragment.MediaDetailFragment
import com.example.gallery.Model.MediaSection

class MediaDetailViewPagerAdapter(
    private val activity: AppCompatActivity,
    private val mediaList: MutableList<MediaSection.Media>
) : FragmentStateAdapter(activity){


    override fun getItemCount(): Int {
        return mediaList.size
    }

    override fun createFragment(position: Int): Fragment {
        return MediaDetailFragment.newInstance(mediaList[position])
    }
}