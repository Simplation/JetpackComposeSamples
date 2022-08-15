package com.simplation.sample.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simplation.sample.ui.theme.SamplesTheme


/**
 * Spacer 能够提供一个空白的布局，可以使用 Modifier.width, Modifier.height 和 Modifier.size 来填充
 */
@Composable
fun SpacerDemo() {
    Row {
        Box(
            modifier = Modifier
                .size(size = 100.dp)
                .background(color = Color.Red)
        )
        Spacer(modifier = Modifier.width(width = 10.dp))
        Box(
            Modifier
                .size(size = 100.dp)
                .background(color = Color.Magenta)
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(
            Modifier
                .size(size = 100.dp)
                .background(color = Color.Black)
        )
    }
}

@Preview
@Composable
fun PreviewSpacer() {
    SamplesTheme {
        SpacerDemo()
    }
}