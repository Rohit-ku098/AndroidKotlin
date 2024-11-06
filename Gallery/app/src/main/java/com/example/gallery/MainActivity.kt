package com.example.gallery

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.Manifest.permission.READ_MEDIA_VIDEO
import android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.gallery.Adapters.ViewPagerAdapter
import com.example.gallery.Fragment.AlbumFragment
import com.example.gallery.Fragment.CameraFragment
import com.example.gallery.Fragment.HomeFragment
import com.example.gallery.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var viewPager: ViewPager2 // View pager is responsible for swiping between fragments
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if(!isPermissionGranted()) {
            requestPermission()
        }


        // set up bottom navigation
        bottomNavigationView = binding.bottomNavigationView
        viewPager = binding.viewPager

        setupViewPager()
        setupBottomNavigationView()

    }


    private fun setupViewPager() {
        val fragments = listOf(
            HomeFragment(),
            AlbumFragment(),
            CameraFragment()
        )
        val adapter = ViewPagerAdapter(this, fragments)
        viewPager.adapter = adapter

        // Handle ViewPager page changes
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bottomNavigationView.menu.getItem(position).isChecked = true

                // Update current fragment name
                binding.currentFragmentName.text = when (position) {
                    0 -> "Photos"
                    1 -> "Albums"
                    2 -> "Camera"
                    else -> "Photos"
                }
            }
        })
    }

    private fun setupBottomNavigationView() {
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> viewPager.currentItem = 0
                R.id.albumFragment -> viewPager.currentItem = 1
                R.id.cameraFragment -> viewPager.currentItem = 2
            }
            true
        }
    }

    fun requestPermission() {
        // Register ActivityResult handler
        val requestPermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
            // Handle permission requests results
            // See the permission example in the Android platform samples: https://github.com/android/platform-samples
        }
        // Permission request logic
        if (
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE &&
                ContextCompat.checkSelfPermission(this, READ_MEDIA_VISUAL_USER_SELECTED) != PERMISSION_GRANTED
            ) {
                requestPermissions.launch(arrayOf(READ_MEDIA_VISUAL_USER_SELECTED))
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && (
                    ContextCompat.checkSelfPermission(this, READ_MEDIA_IMAGES) != PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, READ_MEDIA_VIDEO) != PERMISSION_GRANTED
                )) {
            requestPermissions.launch(arrayOf(READ_MEDIA_IMAGES, READ_MEDIA_VIDEO))
        }
        else {
            requestPermissions.launch(arrayOf(READ_EXTERNAL_STORAGE))
        }
    }

    fun isPermissionGranted(): Boolean {
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE && ContextCompat.checkSelfPermission(this, READ_MEDIA_VISUAL_USER_SELECTED) == PERMISSION_GRANTED) {
            true
        } else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && (
                    ContextCompat.checkSelfPermission(this, READ_MEDIA_IMAGES) == PERMISSION_GRANTED ||
                            ContextCompat.checkSelfPermission(this, READ_MEDIA_VIDEO) == PERMISSION_GRANTED
                    )) {
            true
        } else {
            ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) == PERMISSION_GRANTED
        }
    }

}