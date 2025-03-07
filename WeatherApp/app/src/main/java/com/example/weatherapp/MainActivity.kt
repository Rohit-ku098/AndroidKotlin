package com.example.weatherapp

import android.os.Bundle
import android.renderscript.ScriptGroup.Input
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            WeatherAppTheme {
               getPreview()
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun getPreview() {
    InputField()
}

@Composable
fun InputField() {
    OutlinedTextField(
       value = "",
        onValueChange = {},
        label = { Text("Enter City Name") },
        modifier = Modifier
            .border(1.dp, Color.Black)
            .fillMaxSize()
    )

    Icon(
        modifier = Modifier
            .padding(20.dp),
        tint = Color.Black,
        contentDescription = "Search Icon"
    )
}