package com.example.gallery

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.Manifest.permission.READ_MEDIA_VIDEO
import android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gallery.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
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
        val bottomNavigation = binding.bottomNavigationView
        val navigationController = findNavController(R.id.fragmentContainerView)
        bottomNavigation.setupWithNavController(navigationController)

        val currentFragmentName = binding.currentFragmentName

        navigationController.addOnDestinationChangedListener { _, destination, _ ->
            currentFragmentName.text = when(destination.id) {
                R.id.homeFragment -> "Photos"
                R.id.albumFragment -> "Album"
                else -> "Camera"
            }
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