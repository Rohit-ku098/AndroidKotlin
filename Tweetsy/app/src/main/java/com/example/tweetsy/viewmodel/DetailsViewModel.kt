package com.example.tweetsy.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweetsy.models.TweetItem
import com.example.tweetsy.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor (
    private val repository: TweetRepository,
    savedStateHandle: SavedStateHandle  // When moving from one screen to another,
                                        // the arguments automatically saved into savedStateHandle,
                                        // so to use arguments, we need to inject it
): ViewModel() {
    val tweets: StateFlow<List<TweetItem>>
        get() = repository.tweets

    init {
        viewModelScope.launch {
            repository.getTweets(savedStateHandle["category"] ?: "android")
        }
    }
}