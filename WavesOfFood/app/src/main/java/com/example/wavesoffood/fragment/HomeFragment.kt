package com.example.wavesoffood.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.wavesoffood.Adapter.PopularFoodAdapter
import com.example.wavesoffood.R
import com.example.wavesoffood.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sliderImageList = ArrayList<SlideModel>()
        sliderImageList.add(SlideModel(R.drawable.banner1, ScaleTypes.FIT))
        sliderImageList.add(SlideModel(R.drawable.banner2, ScaleTypes.FIT))
        sliderImageList.add(SlideModel(R.drawable.banner3, ScaleTypes.FIT))

        binding.imageSlider.setImageList(sliderImageList, ScaleTypes.FIT)

        val foodImage = arrayListOf(
            R.drawable.food1,
            R.drawable.food2,
            R.drawable.food3,
            R.drawable.food4
        )

        val foodName = arrayListOf(
            "Herbal Pancake",
            "Fruit Salad",
            "Green Noddle",
            "Pizza"
        )

        val foodPrize = arrayListOf("$5", "$10", "$4", "$15")

        binding.popularFoodRecyclerView.adapter = PopularFoodAdapter(foodName, foodImage, foodPrize)
        binding.popularFoodRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.viewMenuBtn.setOnClickListener {
            val menuBottomSheetDialogFragment = MenuFragment()
            menuBottomSheetDialogFragment.show(requireActivity().supportFragmentManager, "Menu")
        }
    }
}