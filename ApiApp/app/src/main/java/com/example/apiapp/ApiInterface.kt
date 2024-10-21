package com.example.apiapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("products")
    fun getProducts(@Query("limit") limit: Int, @Query("skip") skip: Int): Call<Products>
}