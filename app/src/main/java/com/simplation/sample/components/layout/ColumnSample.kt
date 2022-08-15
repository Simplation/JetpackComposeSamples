package com.simplation.sample.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simplation.sample.ui.theme.SamplesTheme


@Composable
fun ColumnsDemo() {
    Column {
        // 没有权重的子项将会有指定的尺寸

        Box(
            Modifier
                .size(40.dp, 80.dp)
                .background(Color.Magenta)
        )

        // 有权重，这个子项将会占用剩余高度的一半

        Box(
            Modifier
                .width(40.dp)
                .weight(1f)
                .background(Color.Yellow)
        )

        // 有权重，但是 fill 参数是 false，这个子项将会最多占用剩余高度的一半
        // 因此，如果指定的高度较大，它将会占用 80 dp（它的首选高度）
        Box(
            modifier = Modifier
                .size(width = 40.dp, height = 80.dp)
//                .weight(1f, fill = false)
                .weight(1f)
                .background(Color.Green)
        )
    }
}

@Preview
@Composable
fun PreviewColumn() {
    SamplesTheme {
        ColumnsDemo()
    }
}