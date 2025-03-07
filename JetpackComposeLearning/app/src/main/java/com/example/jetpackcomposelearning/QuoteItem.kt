package com.example.jetpackcomposelearning

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.logging.Filter

@Preview()
@Composable
fun quoteItem() {
    Card(
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 5.dp)
    ) {
        Row {
            Image(
                painter = painterResource(R.drawable.ic_quote),
                contentDescription = "quote",

                Modifier.background(Color.Black)

            )

            Column {
                Text(
                    "lorem ipsum dolor sit amet"
                )
            }
        }
    }
}

