package com.simplation.sample.components.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.simplation.sample.ui.theme.GreetingCardTheme

class FloatingActionButtonSample : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GreetingCardTheme {
                FloatingActionButtonDemo()
            }
        }
    }
}

@Composable
fun FloatingActionButtonDemo() {
    FloatingActionButton(onClick = { /*TODO*/ }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Localized description")
    }

    // 包含文本和可选图标的扩展 FAB
    /*ExtendedFloatingActionButton(text = {
        Text(text = "Add")
    }, onClick = { *//*TODO*//* })*/
}

@Preview
@Composable
fun PreviewFloatingActionButton() {
    GreetingCardTheme {
        FloatingActionButtonDemo()
    }
}
