package com.simplation.sample.components.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simplation.sample.ui.theme.GreetingCardTheme

class CardSample : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingCardTheme {
                CardDemo()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardDemo() {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp)
        .clickable { }
//        , elevation = 10.dp
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(buildAnnotatedString {
                append("Welcome ")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.W900, color = Color(0xFF4552B8)
                    )
                ) {
                    append("Jetpack Compose 博物馆")
                }
            })

            Text(buildAnnotatedString {
                append("你现在所处的章节是 ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.W900)) {
                    append("Card")
                }
            })
        }
    }
}

@Preview
@Composable
fun PreviewCardDemo() {
    GreetingCardTheme {
        CardDemo()
    }
}
