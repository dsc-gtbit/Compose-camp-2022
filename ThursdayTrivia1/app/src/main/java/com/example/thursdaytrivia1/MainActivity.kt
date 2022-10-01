package com.example.thursdaytrivia1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import com.example.thursdaytrivia1.ui.theme.ThursdayTrivia1Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThursdayTrivia1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Introduction("Android")
                }
            }
        }
    }
}

@Composable
fun Introduction(name: String) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val image: Painter = painterResource(id = R.drawable.composelogo)
        Image(
            painter = image,
            contentDescription = "",
            modifier = Modifier
                // Set image size to 40 dp
                .size(300.dp)
        )
        Text(text = "$name",
            color = Color(56, 112, 179, 255),
            fontSize = 30.sp,
            modifier = Modifier
                .padding(16.dp)
        )
        Text(
            text = "❤",
            fontSize = 48.sp
        )
        Text(text = "Jetpack Compose",
            color = Color(8, 48, 66, 255),
            fontSize = 30.sp,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThursdayTrivia1Theme {
        Introduction("Kanishk Khurana")
    }
}