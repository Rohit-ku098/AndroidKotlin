package com.example.tweetsy.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tweetsy.R
import com.example.tweetsy.viewmodel.CategoryViewModel

@Composable
fun CategoryScreen(onClick: (String)->Unit) {
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val categories = categoryViewModel.categories.collectAsState()

    if(categories.value.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Loading...", fontSize = 20.sp)
        }
    }
    else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            items(categories.value.distinct()) { category ->
                CategoryItem(category=category, onClick)
            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun CategoryItem(category: String, onClick: (String)->Unit) {
    Box(modifier = Modifier
        .size(160.dp)
        .padding(8.dp)
        .border(BorderStroke(2.dp, Color(0xFFEEEEEE)), shape = RoundedCornerShape(8.dp))
        .clip(RoundedCornerShape(8.dp))
        .paint(
            painter = painterResource(R.drawable.wave),
            alignment = Alignment.TopStart,
            contentScale = ContentScale.FillBounds
        )
        .clickable {
            onClick(category)
        },
        contentAlignment = Alignment.BottomCenter

    ) {
        Text(
            text = category,
            modifier = Modifier.padding(8.dp),
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}