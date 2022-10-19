package com.simplation.sample.components.layout

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.simplation.sample.ui.theme.SamplesTheme


/**
 * 使用 Modifier.layout() 手动控制元素的测量和布局。
 *
 * 当使用 layout 修饰符时，你传入的回调 lambda 需要包含两个参数：measurable、constraints
 * - measurable：子元素的测量句柄，通过提供的 api 完成测量与布局过程
 * - constraints: 子元素的测量约束，包括宽度与高度的最大值与最小值。
 */
@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.firstBaselineToTop(firstBaselineToTop: Dp) =
    Modifier.layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
        val firstBaseline = placeable[FirstBaseline]
        val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
        val height = placeable.height + placeableY
        layout(placeable.width, height) {
            placeable.placeRelative(0, placeableY)
        }
    }

@Preview
@Composable
fun TextWithPaddingToBaselinePreview() {
    SamplesTheme {
        Text(text = "Hi there!", modifier = Modifier.firstBaselineToTop(24.dp))
    }
}

@Preview
@Composable
fun TextWithNormalPaddingPreview() {
    SamplesTheme {
        Text(text = "Hi there!", modifier = Modifier.padding(top = 24.dp))
    }
}


/**
 * Layout Modifier 会将当前元素的所有子元素视作为整体进行统一的测量与布局，多适用于统一处理的场景。
 * 如果需要精细化测量布局每一个子组件，这需要进行完全的自定义 Layout。类似于传统 View 系统中定制 View 与 ViewGroup
 * 测量布局流程的区别。对于定制 “ViewGroup” 的场景，应该使用 Layout Composable.
 *
 * Layout 需要填写三个参数：modifier，content，measurePolicy
 * - modifier：由外部传入的修饰符，会决定该 UI 元素的 constraints
 * - content：在 content 中声明所有子元素信息
 * - measurePolicy：默认场景下只实现 measure 即可，上面示例中最后传入的 lambda 就是 measure 的实现。
 *
 * 当你想要为你的 Layout Composable 适配 Intrinsics 时(官方中文翻译为固有特性测量)，
 * 则需要重写 minIntrinsicWidth 、minIntrinsicHeight、maxIntrinsicWidth 、maxIntrinsicHeight 方法。
 */

@Composable
fun MyOwnColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }
        var yPosition = 0
        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEach { placeable ->
                placeable.placeRelative(x = 0, y = yPosition)
                yPosition += placeable.height
            }
        }
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    MyOwnColumn(modifier.padding(8.dp)) {
        Text("MyOwnColumn")
        Text("places items")
        Text("vertically.")
        Text("We've done it by hand!")
    }
}


@Preview
@Composable
fun PreviewCustomLayout() {
    SamplesTheme {
        BodyContent()
    }
}