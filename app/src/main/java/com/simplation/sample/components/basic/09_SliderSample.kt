package com.simplation.sample.components.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.simplation.sample.ui.theme.SamplesTheme

class SliderSample : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SamplesTheme {
                SliderDemo()
            }
        }
    }
}

@Composable
fun SliderDemo() {
    // Slider 允许用户从一定范围的数值中进行选择。
    // Slider 反映了一个沿条的数值范围，用户可以从中选择一个单一的数值。它们是调整音量、亮度或应用图像过滤器等设置的理想选择。

    var progress by remember {
        mutableStateOf(0f)
    }

    Slider(
        value = progress,
        colors = SliderDefaults.colors(
            thumbColor = Color.White,
            activeTickColor = Color(0xFF0079D3)
        ), onValueChange = {
            progress = it
        }
    )
}

@Preview
@Composable
fun PreviewSliderDemo() {
    SamplesTheme {
        SliderDemo()
    }
}
