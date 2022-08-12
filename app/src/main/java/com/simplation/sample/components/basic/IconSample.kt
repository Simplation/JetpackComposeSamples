package com.simplation.sample.components.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.simplation.sample.ui.theme.GreetingCardTheme

class IconSample : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GreetingCardTheme {
                IconDemo()
            }
        }
    }
}

@Composable
fun IconDemo() {
    Row {
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null, tint = Color.Gray)
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null, tint = Color.Red)
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null, tint = Color.DarkGray)
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null, tint = Color.Blue)
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null, tint = Color.Yellow)
    }
}

@Preview
@Composable
fun PreviewIcon() {
    GreetingCardTheme {
        IconDemo()
    }
}