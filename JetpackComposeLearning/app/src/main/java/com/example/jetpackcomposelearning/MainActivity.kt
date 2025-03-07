package com.example.jetpackcomposelearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposelearning.ui.theme.JetpackComposeLearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}

@Preview(showBackground = true, heightDp = 500, widthDp = 300)
@Composable
fun GetPreview() {
//    Column {
//        Item(R.drawable.a, "Rohit", "Android Developer")
//        Item(R.drawable.b, "Vivek", "Android Developer")
//        Item(R.drawable.c, "Karan", "Android Developer")
//        Item(R.drawable.d, "Chaitanya", "Android Developer")
//    }
    lazyGridView()

}

@Composable
fun Item(imgId: Int, name: String, degination: String) {
    Row(Modifier.padding(5.dp)) {
        Image(
            painter = painterResource(id = imgId),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
                .padding(6.dp)
        )
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                degination,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


data class CardData(val height: Int, val color: Color)
val cardDataList = listOf(
    CardData(100, Color.Green),
    CardData(200, Color.Yellow),
    CardData(150, Color.Cyan),
    CardData(100, Color.White),
    CardData(300, Color.Magenta),
    CardData(50, Color.Red),
)

@Composable
fun lazyGridView() {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 5.dp, vertical = 5.dp),
        modifier = Modifier.fillMaxSize(),
        verticalItemSpacing = 5.dp,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(cardDataList) { card ->
            gridItem(card.height, card.color)
        }
    }
}

@Composable
fun gridItem(height: Int, color: Color) {
    Card(
        modifier = Modifier
            .height(height.dp),

        elevation = CardDefaults.elevatedCardElevation(5.dp),
        colors = CardDefaults.cardColors(color)
    ) {
        Text(text = "Hello")
    }
}
