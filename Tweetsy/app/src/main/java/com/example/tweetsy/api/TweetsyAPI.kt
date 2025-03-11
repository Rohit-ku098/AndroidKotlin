package com.example.tweetsy.api

import com.example.tweetsy.models.TweetItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetsyAPI {

    @GET("/v3/b/67c92397acd3cb34a8f5d3a7?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category: String): Response<List<TweetItem>>

    @GET("/v3/b/67c92397acd3cb34a8f5d3a7?meta=false")
    @Headers("X-JSON-Path: tweets..category")
    suspend fun getCategories(): Response<List<String>>
}