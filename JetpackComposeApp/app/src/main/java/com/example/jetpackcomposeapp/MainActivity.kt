package com.example.jetpackcomposeapp

import android.os.Bundle
import android.widget.ScrollView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.buildSpannedString
import com.example.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            val scrollState = rememberScrollState(0)
            JetpackComposeAppTheme {
                Scaffold(
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                ) { paddingValues ->
                    Column(
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxSize()
                            .padding(12.dp)
                            .verticalScroll(scrollState),

                    ) {
                        val painter = painterResource(id=R.drawable.littlegirl)
                        val title = "A Smiling Little Girl"
                        val contentDescription = "A Smiling Little Girl"
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(10.dp)
                        ) {
                            ImageCard(painter, contentDescription, title)
                        }

                        StylishText()

                        state()

                        TextFieldAndSnackbar(snackbarHostState)

                        Lists()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, heightDp = 500, widthDp = 300)
@Composable
fun preview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        state()
    }
}

@Composable
fun rowcol() {
    Row(
        modifier = Modifier
            .background(Color.Green)
            .fillMaxSize(1f)
            .padding(10.dp)
            .border(5.dp, Color.Red)
    ) {
        Text("Hello")
        Text("World")
    }
}

@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = modifier
            .height(300.dp)
    ) {
        Box {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                modifier = Modifier
                    .fillMaxSize(),

                contentScale = ContentScale.Crop
            )
            Box (
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = 300f
                        )
                    )
                    .fillMaxSize()
            )
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ) {
                Text(text = title, style = TextStyle(fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Medium))
            }

        }
    }
}

@Composable
fun StylishText() {

    val lato = FontFamily(
        Font(R.font.lato_bold, FontWeight.Bold),
        Font(R.font.lato_thin, FontWeight.Thin),
        Font(R.font.lato_black, FontWeight.Black),
        Font(R.font.lato_light, FontWeight.Light),
        Font(R.font.lato_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.lato_black_italic, FontWeight.Black, FontStyle.Italic),
        Font(R.font.lato_thin_italic, FontWeight.Thin, FontStyle.Italic),
        Font(R.font.lato_regular, FontWeight.Normal),
        Font(R.font.lato_light_italic, FontWeight.Light, FontStyle.Italic),
        Font(R.font.lato_bold_italic, FontWeight.Bold, FontStyle.Italic),
    )
    Text(
        text= buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Green, fontSize = 50.sp)) {
                append("J")
            }
            append("etpack")
            withStyle(style = SpanStyle(color = Color.Green, fontSize = 50.sp)) {
                append("C")
            }
            append("ompose")
        },
        textDecoration = TextDecoration.Underline,
        fontFamily = lato,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 30.sp
    )
}

@Composable
fun state() {
    val color = remember { mutableStateOf(Color.Yellow) }
    Box(
        modifier = Modifier
            .height(200.dp)
            .width(200.dp)
            .background(color = color.value)
            .clickable {
                color.value = Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )
            }
    ) {
        Text(
            text = "Click Me",
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
fun TextFieldAndSnackbar(snackbarHostState: SnackbarHostState) {
    var text by remember { mutableStateOf("") }
    var scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        TextField(
            value= text,
            label = {Text("Enter your name")},
            onValueChange = { text = it}
        )

        Button(
            onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar("Hello $text")
                }
            }
        ) {
            Text(text = "Greet Me")
        }
    }
}

@Composable
fun Lists() {
    LazyColumn(
        modifier = Modifier.height(300.dp)
    ) {

        item() {
            Text(
                text = "Items",
                color = Color.Green,
                style = TextStyle(
                    fontSize = 30.sp
                )
            )
        }
        items(5) {
            Text(
                text = "Item $it",
                color = Color.Green
            )
        }

        item() {
            Text(
                text = "ItemsIndexed",
                color = Color.Green,
                style = TextStyle(
                    fontSize = 30.sp
                )
            )
        }
        itemsIndexed( items = listOf("Lorem", "ipsum", "dolor", "sit", "amet")) { index, item ->
            Text(text = "Item $index: $item")
        }
    }
}