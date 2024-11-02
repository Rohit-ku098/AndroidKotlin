package com.example.gallery.Fragment

import android.content.res.Configuration
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gallery.Adapters.GalleryAdapter
import com.example.gallery.Model.MediaSection
import com.example.gallery.R
import com.example.gallery.ViewModel.HomeViewModel
import com.example.gallery.ViewModel.HomeViewModelFactory
import com.example.gallery.databinding.FragmentHomeBinding
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var mediaList: MutableList<MediaSection>
    lateinit var galleryAdapter: GalleryAdapter
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel = ViewModelProvider(requireActivity(), HomeViewModelFactory(requireContext().contentResolver)).get(HomeViewModel::class.java)
        mediaList = mutableListOf<MediaSection>()
        galleryAdapter = GalleryAdapter(requireContext(), mediaList)
        binding.homeRecyclerView.adapter = galleryAdapter
        setupGridLayoutManager()

        //TODO:Optimise data updation
        homeViewModel.mediaList.observe(viewLifecycleOwner) {
            Log.d("MediaList", it.toString())
            mediaList.clear()
            mediaList.addAll(it)
            galleryAdapter.notifyDataSetChanged()
        }
    }

    private fun setupGridLayoutManager() {
        val spanCount = calculateSpanCount()
        val gridLayoutManager= GridLayoutManager(requireContext(), spanCount)

        // Setting span size for date header and media item
        gridLayoutManager.spanSizeLookup = object:GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (mediaList[position]) {
                    is MediaSection.DateHeader -> spanCount // Span the entire row
                    else -> 1 // Media item only take one column
                }
            }
        }

        binding.homeRecyclerView.layoutManager = gridLayoutManager
        galleryAdapter.updateItemWidth(resources.displayMetrics.widthPixels / spanCount)

    }

    private fun calculateSpanCount(): Int {
        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val itemWidth = resources.getDimensionPixelSize(R.dimen.item_width)
        val spanCount = (screenWidth / itemWidth).coerceAtLeast(1)
        return spanCount
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setupGridLayoutManager() // Recalculate layout and item width on rotation
    }
}