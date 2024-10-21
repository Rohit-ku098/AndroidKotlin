package com.example.wavesoffood.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wavesoffood.Adapter.CartItemAdapter
import com.example.wavesoffood.Adapter.MenuAdapter
import com.example.wavesoffood.R
import com.example.wavesoffood.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: MenuAdapter

    val originalMenuItemsImage = mutableListOf(
        R.drawable.food1,
        R.drawable.food2,
        R.drawable.food3,
        R.drawable.food4,
        R.drawable.food1,
        R.drawable.food2,
        R.drawable.food3,
        R.drawable.food4
    )
    val originalMenuItemsName = mutableListOf(
        "Herbal Pancake",
        "Fruit Salad",
        "Green Noddle",
        "Pizza",
        "Herbal Pancake",
        "Fruit Salad",
        "Green Noddle",
        "Pizza"
    )
    val orginalMenuItemsPrice = mutableListOf("$5", "$10", "$4", "$15","$5", "$10", "$4", "$15")

    val filteredMenuItemsImage = mutableListOf<Int>()
    val filteredMenuItemsName = mutableListOf<String>()
    val filteredMenuItemsPrice = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val menuRecyclerView = binding.menuRecyclerView
        menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = MenuAdapter(filteredMenuItemsName,filteredMenuItemsImage,filteredMenuItemsPrice)
        menuRecyclerView.adapter = adapter

        setupSearchView()
        showAllMenu()
    }

    private fun showAllMenu() {
        filteredMenuItemsPrice.clear()
        filteredMenuItemsName.clear()
        filteredMenuItemsImage.clear()

        filteredMenuItemsName.addAll(originalMenuItemsName)
        filteredMenuItemsImage.addAll(originalMenuItemsImage)
        filteredMenuItemsPrice.addAll(orginalMenuItemsPrice)
        adapter.notifyDataSetChanged()
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterMenuItem(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterMenuItem(newText.toString())
                return true
            }
        })
    }

    private fun filterMenuItem(query: String) {
        filteredMenuItemsImage.clear()
        filteredMenuItemsName.clear()
        filteredMenuItemsPrice.clear()

        originalMenuItemsName.forEachIndexed { index, item ->
            if(item.contains(query, ignoreCase = true)) {
                filteredMenuItemsName.add(item)
                filteredMenuItemsImage.add(originalMenuItemsImage[index])
                filteredMenuItemsPrice.add(orginalMenuItemsPrice[index])
            }
        }

        adapter.notifyDataSetChanged()
    }
}