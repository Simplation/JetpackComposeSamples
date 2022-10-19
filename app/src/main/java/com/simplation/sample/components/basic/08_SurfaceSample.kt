package com.simplation.sample.components.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simplation.sample.ui.theme.SamplesTheme

class SurfaceSample : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SamplesTheme {
                SurfaceDemo()
            }
        }
    }
}

/**
 * Surface 主要负责：
 *
 * 剪裁：Surface 会根据 shape 属性所描述的形状来裁剪它的子元素。
 * 高度：Surface 会绘制阴影来表示平面的深度，而这个深度由高度属性 (Elevation) 表示。如果传递的形状是凹进去的，那么在 Android 版本小于 10 的情况下，阴影不会被画出来。
 * 边框：如果形状有边框，那么它也会被画出来。
 * 背景：Surface 在 shape 指定的形状上填充颜色。如果颜色是 Colors.surface，将使用 LocalElevationOverlay 中的 ElevationOverlay 来进行叠加--默认情况下，这只会发生在深色主题中。覆盖的颜色取决于这个 Surface 的高度，以及任何父级 Surface 设置的 LocalAbsolutelevation。这可以确保一个 Surface 的叠加高度永远不会比它的祖先低，因为它是所有先前 Surface 的高度的总和
 * 内容颜色：Surface 使用 contentColor 为这个平面的内容指定一个首选的颜色--这个颜色被文本和图标组件作为默认颜色使用
 */
@Composable
fun SurfaceDemo() {
    Surface(
        shape = RoundedCornerShape(10.dp),
        shadowElevation = 10.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(modifier = Modifier
            .clickable { }
            .padding(15.dp)) {
            Text(buildAnnotatedString {
                append("欢迎来到 ")
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.W900, color = Color(0xFF4552B8))
                ) {
                    append("Jetpack Compose 博物馆")
                }
            })

            Text(buildAnnotatedString {
                append("你现在所处的章节是 ")
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.W900)
                ) {
                    append("Surface")
                }
            })
        }
    }
}

@Preview(device = "id:Nexus 6", showSystemUi = true)
@Composable
fun PreviewSurface() {
    SamplesTheme {
        SurfaceDemo()
    }
}
