package com.example.wavesoffood.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wavesoffood.Adapter.HistoryFoodAdapter
import com.example.wavesoffood.R
import com.example.wavesoffood.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {
    lateinit var binding: FragmentHistoryBinding
    lateinit var adapter: HistoryFoodAdapter
    val historyItemsImage = mutableListOf(
        R.drawable.food1,
        R.drawable.food2,
        R.drawable.food3,
    )
    val historyItemsName = mutableListOf(
        "Herbal Pancake",
        "Fruit Salad",
        "Green Noddle",
    )
    val historyItemsPrice = mutableListOf("$5", "$10", "$4")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = HistoryFoodAdapter(historyItemsImage, historyItemsName, historyItemsPrice)

        val historyRecyclerView = binding.historyRecyclerView
        historyRecyclerView.adapter = adapter
        historyRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}