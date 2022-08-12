package com.simplation.sample.components.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.simplation.sample.ui.theme.GreetingCardTheme

class ButtonSample : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingCardTheme {
                ButtonDemo()
            }
        }
    }
}

@Composable
fun ButtonDemo() {
    Button(onClick = { /*TODO*/ }) {
        Icon(
            // Material 库中的图标，有 Filled, Outlined, Rounded, Sharp, Two Tone 等
            imageVector = Icons.Filled.Favorite,
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = "喜欢")
    }
}

@Preview
@Composable
fun PreviewButtonDemo() {
    GreetingCardTheme {
        ButtonDemo()
    }
}
