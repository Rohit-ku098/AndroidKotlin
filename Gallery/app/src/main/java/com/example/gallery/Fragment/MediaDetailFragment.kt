package com.example.gallery.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.MediaController
import android.widget.VideoView
import com.bumptech.glide.Glide
import com.example.gallery.Model.MediaSection
import com.example.gallery.databinding.FragmentMediaDetailBinding


class MediaDetailFragment : Fragment() {
    lateinit var binding: FragmentMediaDetailBinding
    lateinit var videoView: VideoView
    lateinit var imageView: ImageView
    lateinit var media: MediaSection.Media

    companion object {
        private const val ARG_MEDIA_DETAILS = "com.example.gallery.Fragment.mediaDetails"

        fun newInstance(media: MediaSection.Media): MediaDetailFragment {
            val fragment = MediaDetailFragment()
            val args = Bundle().apply {
                putParcelable(ARG_MEDIA_DETAILS, media)
            }
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMediaDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("ImageDetailFragment", "onViewCreated: ")
        super.onViewCreated(view, savedInstanceState)

        videoView = binding.videoDetailVideoView
        imageView = binding.imageDetailImageView
        media = arguments?.getParcelable(ARG_MEDIA_DETAILS)!!

        setUpMedia()

        videoView.setOnClickListener {
            toggleVidePlayback()
        }
    }

    private fun setUpMedia() {
        if(media.isVideo) {
            videoView.visibility = View.VISIBLE
            imageView.visibility = View.GONE
            setupVideo()
        } else {
            videoView.visibility = View.GONE
            imageView.visibility = View.VISIBLE
            media?.let {
                Glide.with(this).load(it.uri).into(imageView)
            }
        }
    }

    private fun setupVideo() {
        videoView.setVideoURI(media?.uri)

        // Set up media controls
        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        // Start the video
        videoView.start()
    }

    private fun toggleVidePlayback() {
        if(videoView.isPlaying) {
            videoView.pause()
        } else {
            videoView.start()
        }
    }
}