package com.example.gallery.Fragment

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import com.example.gallery.R
import com.example.gallery.Utils.calculateSpanCount
import com.example.gallery.databinding.FragmentAlbumBinding

class AlbumFragment : Fragment() {
    lateinit var binding: FragmentAlbumBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val primaryAlbumsGridView = binding.primaryAlbumGridView
        primaryAlbumsGridView.columnCount = calculateSpanCount(resources, resources.getDimensionPixelSize(R.dimen.album_item_width))
        primaryAlbumsGridView.children.forEach {
            it.layoutParams.width = resources.getDimensionPixelSize(R.dimen.album_item_width)
        }
    }
}