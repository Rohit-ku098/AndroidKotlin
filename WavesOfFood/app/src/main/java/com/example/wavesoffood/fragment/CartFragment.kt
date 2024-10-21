package com.example.wavesoffood.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wavesoffood.Adapter.CartItemAdapter
import com.example.wavesoffood.R
import com.example.wavesoffood.databinding.FragmentCartBinding


class CartFragment : Fragment() {
   lateinit var binding: FragmentCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cartItemsImage = arrayListOf(
            R.drawable.food1,
            R.drawable.food2,
            R.drawable.food3,
            R.drawable.food4,
            R.drawable.food1,
            R.drawable.food2,
            R.drawable.food3,
            R.drawable.food4
        )
        val cartItemsName = arrayListOf(
            "Herbal Pancake",
            "Fruit Salad",
            "Green Noddle",
            "Pizza",
            "Herbal Pancake",
            "Fruit Salad",
            "Green Noddle",
            "Pizza"
        )
        val cartItemsPrice = arrayListOf("$5", "$10", "$4", "$15","$5", "$10", "$4", "$15")
        val cartItemsQuantity = arrayListOf(1,4,5,3,1,4,5,3)


        val cartRecyclerView = binding.cartRecyclerView
        cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val cartRecyclerViewAdapter = CartItemAdapter(cartItemsName, cartItemsPrice, cartItemsQuantity, cartItemsImage, )
        cartRecyclerView.adapter = cartRecyclerViewAdapter

        cartRecyclerViewAdapter.setOnClickListener(
            object: CartItemAdapter.onClickListener {
                override fun onClickDelete(position: Int) {
                    cartItemsPrice.removeAt(position)
                    cartItemsName.removeAt(position)
                    cartItemsQuantity.removeAt(position)
                    cartItemsImage.removeAt(position)
                    cartRecyclerViewAdapter.notifyItemRemoved(position)
                    cartRecyclerViewAdapter.notifyItemRangeChanged(position, cartItemsName.size)
                }

                override fun onClick(position: Int) {
                    Toast.makeText(requireContext(), "onClick $position", Toast.LENGTH_SHORT).show()
                }

                override fun onClickIncrement(position: Int) {
                    if(cartItemsQuantity[position] < 10) {
                        cartItemsQuantity[position] += 1
                        cartRecyclerViewAdapter.notifyItemChanged(position)
                    }
                }

                override fun onClickDecrement(position: Int) {
                    if(cartItemsQuantity[position] > 1) {
                        cartItemsQuantity[position] -= 1
                        cartRecyclerViewAdapter.notifyItemChanged(position)
                    }
                }

            }
        )
    }

}