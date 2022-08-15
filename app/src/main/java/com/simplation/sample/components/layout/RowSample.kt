package com.simplation.sample.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simplation.sample.ui.theme.SamplesTheme

@Composable
fun RowDemo() {

    // Row 会将里面的子项放在一个水平的序列中
    // Row 布局能够根据使用 RowScope.weight 修改器提供的权重来分配里面子项的宽度
    // 如果一个子项没有提供权重的话，会使用这个子项默认的宽度，再根据其他剩余有权重的子项计算剩余的空间
    Row {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Magenta)
        )

        Box(
            modifier = Modifier
                .size(40.dp)
                .weight(1f)
                .background(Color.Yellow)
        )

        Box(
            modifier = Modifier
                .size(40.dp)
                .weight(1f)
                .background(Color.Green)
        )

        // Row 不设置 weight 的时候, 会尽可能的小子项贴合在一起, 类似于 xml 中的 wrap_content 属性
        Row {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Magenta)
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Yellow)
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Green)
            )
        }
    }
}

@Preview
@Composable
fun PreviewRow() {
    SamplesTheme {
        RowDemo()
    }
}