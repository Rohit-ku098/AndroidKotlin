package com.example.wavesoffood

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.wavesoffood.databinding.ActivityChooseLocationBinding

class ChooseLocationActivity : AppCompatActivity() {
    lateinit var binding: ActivityChooseLocationBinding
    lateinit var listOfLocation : ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseLocationBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        listOfLocation = arrayListOf(
            "Choose Location","Delhi", "Noida","Mumbai", "Bhopal", "Chandigarh", "Jaipur", "Kolkata", "Patna", "Kanpur",
            "Hyderabad", "Ahmedabad", "Surat", "Chennai", "Lucknow", "Vadodara", "Indore", "Pune",
            "Bangalore", "Mysore", "Cochin", "Coimbatore", "Nagpur", "Thane", "Thiruvananthapuram",
            "Visakhapatnam", "Vizag", "Kozhikode", "Ernakulam", "Palakkad", "Thrissur", "Malappuram",
        )

        val listAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfLocation)
        val chooseLocationInput = binding.listOfLocations
        chooseLocationInput.setAdapter(listAdapter)
        chooseLocationInput.setOnItemClickListener {
            parent, view, position, id ->
                if(position != 0) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
        }


    }
}