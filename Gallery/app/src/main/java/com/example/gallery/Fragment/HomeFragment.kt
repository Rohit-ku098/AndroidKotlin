package com.example.gallery.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gallery.Adapters.GalleryAdapter
import com.example.gallery.Model.Media
import com.example.gallery.ViewModel.HomeViewModel
import com.example.gallery.ViewModel.HomeViewModelFactory
import com.example.gallery.databinding.FragmentHomeBinding
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var mediaList: MutableList<Media>
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
        mediaList = mutableListOf<Media>()
        galleryAdapter = GalleryAdapter(requireContext(), mediaList)
        binding.homeRecyclerView.adapter = galleryAdapter
        binding.homeRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)

        homeViewModel.mediaList.observe(viewLifecycleOwner) {
            mediaList.clear()
            mediaList.addAll(it)
            galleryAdapter.notifyDataSetChanged()
        }
    }

}