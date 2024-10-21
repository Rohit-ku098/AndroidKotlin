package com.example.wavesoffood.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wavesoffood.Adapter.MenuAdapter
import com.example.wavesoffood.R
import com.example.wavesoffood.databinding.FragmentMenuBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MenuFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuItemsImage = mutableListOf(
            R.drawable.food1,
            R.drawable.food2,
            R.drawable.food3,
            R.drawable.food4,
            R.drawable.food1,
            R.drawable.food2,
            R.drawable.food3,
            R.drawable.food4
        )
        val menuItemsName = mutableListOf(
            "Herbal Pancake",
            "Fruit Salad",
            "Green Noddle",
            "Pizza",
            "Herbal Pancake",
            "Fruit Salad",
            "Green Noddle",
            "Pizza"
        )
        val menuItemsPrice = mutableListOf("$5", "$10", "$4", "$15","$5", "$10", "$4", "$15")

        val menuRecyclerView = binding.menuRecyclerView
        menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val menuRecyclerViewAdapter = MenuAdapter(menuItemsName,menuItemsImage,menuItemsPrice)
        menuRecyclerView.adapter = menuRecyclerViewAdapter

        binding.menuBackBtn.setOnClickListener {
            dismiss()
        }
    }


}