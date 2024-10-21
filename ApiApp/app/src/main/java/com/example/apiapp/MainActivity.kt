package com.example.apiapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.AbsListView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var retrofitBuilder: ApiInterface
    lateinit var productList: MutableList<Product>
    private lateinit var manager: LinearLayoutManager
    private lateinit var progressBar: ProgressBar
    private lateinit var productRecyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter

    var isScrolling = false
    var currentItem: Int = 0
    var totalItem: Int = 0
    var scrollOutItem: Int = 0

    var limit = 10
    var page = 0
    var skip = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        manager = LinearLayoutManager(this)
        productList = mutableListOf<Product>()
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        productRecyclerView = findViewById<RecyclerView>(R.id.productRecyclerView)
        productAdapter = ProductAdapter(productList)
        fetchData()
        Log.d(TAG, "Product List : $productList")

        productRecyclerView.adapter = productAdapter
        productRecyclerView.layoutManager = manager

        // Fetch more data on scroll
        productRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItem = manager.childCount
                totalItem = manager.itemCount
                scrollOutItem = manager.findFirstVisibleItemPosition()

                if(isScrolling && (currentItem + scrollOutItem == totalItem)){
                    isScrolling = false
                    fetchData()
                }
            }
        })

    }

    private fun fetchData() {
        progressBar.visibility = ProgressBar.VISIBLE

        skip = page * limit
        page += 1
        val productsData = retrofitBuilder.getProducts(limit, skip)
        productsData.enqueue(object : Callback<Products?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Products?>, response: Response<Products?>) {
                val productBody = response.body()
                for(product in productBody?.products!!){
                    productList.add(product)
                }
                progressBar.visibility = ProgressBar.GONE
                productAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Products?>, t: Throwable) {
                Log.e(TAG, "onFailure: " + t.message)
            }
        })
    }
}