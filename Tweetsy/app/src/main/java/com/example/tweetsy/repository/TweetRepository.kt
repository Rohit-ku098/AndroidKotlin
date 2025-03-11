package com.example.tweetsy.repository

import com.example.tweetsy.api.TweetsyAPI
import com.example.tweetsy.models.TweetItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetRepository @Inject constructor(private val tweetsyAPI: TweetsyAPI) {
    private val _category: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    val category: StateFlow<List<String>>
        get() = _category

    private val _tweets: MutableStateFlow<List<TweetItem>> = MutableStateFlow(emptyList())
    val tweets: StateFlow<List<TweetItem>>
        get() = _tweets

    suspend fun getCategories() {
        val response = tweetsyAPI.getCategories()
        if(response.isSuccessful && response.body() != null) {
            _category.emit(response.body()!!)
        }
    }

    suspend fun getTweets(category: String) {
        val response = tweetsyAPI.getTweets("tweets[?(@.category==\"$category\")]")
        if(response.isSuccessful && response.body() != null) {
            _tweets.emit(response.body()!!)
        }
   }
}