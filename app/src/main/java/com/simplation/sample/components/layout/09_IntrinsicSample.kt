package com.simplation.sample.components.layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.simplation.sample.ui.theme.SamplesTheme

class IntrinsicSample : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SamplesTheme {
                IntrinsicDemo()
            }
        }
    }
}

// 在基础组件中使用固有特性测量
@Composable
fun TwoTexts(modifier: Modifier = Modifier, text1: String, text2: String) {
    Row(modifier = modifier.height(IntrinsicSize.Min)) {  // here
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .wrapContentWidth(Alignment.Start),
            text = text1
        )

        Divider(
            color = Color.Black, modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )

        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
                .wrapContentWidth(Alignment.End),
            text = text2
        )
    }
}

@Preview
@Composable
fun PreviewTwoTexts() {
    SamplesTheme {
        Surface {
            TwoTexts(text1 = "Hi", text2 = "there")
        }
    }
}

// 为自定义 Layout 适配固有特性测量
@Composable
fun IntrinsicRow(modifier: Modifier, content: @Composable () -> Unit) {
    Layout(modifier = modifier, content = content, measurePolicy = object : MeasurePolicy {
        override fun MeasureScope.measure(
            measurables: List<Measurable>,
            constraints: Constraints
        ): MeasureResult {
            val deviceConstraints = constraints.copy(minHeight = 0)
            val mainPlaceables =
                measurables.filter { it.layoutId == "main" }.map { it.measure(constraints) }

            val dividePlaceable =
                measurables.first { it.layoutId == "divider" }.measure(deviceConstraints)
            val midPos = constraints.maxWidth / 2
            return layout(width = constraints.maxWidth, height = constraints.maxHeight) {
                mainPlaceables.forEach {
                    it.placeRelative(x = 0, y = 0)
                }
                dividePlaceable.placeRelative(midPos, 0)
            }
        }

        override fun IntrinsicMeasureScope.minIntrinsicHeight(
            measurables: List<IntrinsicMeasurable>,
            width: Int
        ): Int {
            var maxHeight = 0
            measurables.forEach {
                maxHeight = it.minIntrinsicHeight(width).coerceAtLeast(maxHeight)
            }
            return maxHeight
        }

        override fun IntrinsicMeasureScope.maxIntrinsicHeight(
            measurables: List<IntrinsicMeasurable>,
            width: Int
        ): Int {
            TODO("Not yet implemented")
        }

        override fun IntrinsicMeasureScope.maxIntrinsicWidth(
            measurables: List<IntrinsicMeasurable>,
            height: Int
        ): Int {
            TODO("Not yet implemented")
        }

        override fun IntrinsicMeasureScope.minIntrinsicWidth(
            measurables: List<IntrinsicMeasurable>,
            height: Int
        ): Int {
            TODO("Not yet implemented")
        }
    })
}

@Preview
@Composable
fun PreviewIntrinsicRow() {
    SamplesTheme {
        IntrinsicRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Text(
                text = "Left", modifier = Modifier
                    .wrapContentWidth(Alignment.Start)
                    .layoutId("main")
            )

            Divider(
                color = Color.Black,
                modifier = Modifier
                    .width(4.dp)
                    .fillMaxHeight()
                    .layoutId("divider")
            )

            Text(
                text = "Right", modifier = Modifier
                    .wrapContentWidth(Alignment.End)
                    .layoutId("main")
            )
        }
    }

}

@Composable
private fun IntrinsicDemo() {

}