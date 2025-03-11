package com.example.tweetsy.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tweetsy.viewmodel.DetailsViewModel

@Composable
fun DetailScreen() {
    val detailsViewModel: DetailsViewModel = hiltViewModel()
    val tweets = detailsViewModel.tweets.collectAsState()

    Log.d("TweetsyTag", "DetailScreen: ${tweets.value.size} tweets")

    if(tweets.value.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Loading...", fontSize = 20.sp)
        }
    }
    else {
        LazyColumn {
            items(tweets.value) { tweetItem ->
                TweetItem(tweetItem.tweet)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TweetItem(tweet: String = "Lorem ipsum dolor sit amet") {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        border = BorderStroke(1.dp, color = Color(0xFFCCCCCC))
    ) {
        Text(
            text = tweet,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge)
    }

}