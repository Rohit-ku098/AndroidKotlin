package com.example.gallery

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.gallery.Adapters.MediaDetailViewPagerAdapter
import com.example.gallery.Fragment.HomeFragment.Companion.EXTRA_POSITION
import com.example.gallery.Model.MediaSection
import com.example.gallery.ViewModel.GalleryApplication
import com.example.gallery.ViewModel.HomeViewModel
import com.example.gallery.databinding.ActivityMediaDetailBinding


class MediaDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityMediaDetailBinding
    var galleryMediaList : MutableList<MediaSection.Media> = mutableListOf()
    lateinit var homeViewModel: HomeViewModel
    lateinit var mediaDetailViewPagerAdapter: MediaDetailViewPagerAdapter
    lateinit var mediaDetailViewPager: ViewPager2



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMediaDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val position = intent.getIntExtra(EXTRA_POSITION, 0)

        mediaDetailViewPager = binding.mediaDetailViewPager


        // Set up media detail view pager
        homeViewModel = (application as GalleryApplication).homeViewModel
        homeViewModel.mediaList.observe(this) {
            galleryMediaList.clear()
            val mediaList = it.filter { media -> media is MediaSection.Media } as MutableList<MediaSection.Media>
            galleryMediaList.addAll(mediaList)

            mediaDetailViewPagerAdapter = MediaDetailViewPagerAdapter(this@MediaDetailActivity, galleryMediaList)
            mediaDetailViewPager.adapter = mediaDetailViewPagerAdapter
            mediaDetailViewPagerAdapter.notifyDataSetChanged()
            mediaDetailViewPager.setCurrentItem(position, false)
        }
    }

}