package com.simplation.sample.components.basic

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simplation.sample.R
import com.simplation.sample.ui.theme.SamplesTheme
import androidx.compose.runtime.CompositionLocalProvider as CompositionLocalProvider1

class TextSample : ComponentActivity() {

    // https://compose-museum.gitee.io/hello-compose/elements/text/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SamplesTheme {
                TextDemo()
            }
        }
    }
}

@Composable
fun TextDemo() {
    // Text 是 Compose 中最基本的布局组件，它可以显示文字

    // style 参数
    // 文字间距、字体大小
    /*Column {
        Text(
            text = stringResource(id = R.string.content1),
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = stringResource(id = R.string.content2),
            style = MaterialTheme.typography.headlineSmall
        )
    }*/

    Column(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.content), style = TextStyle(
                fontWeight = FontWeight.W900, fontSize = 20.sp, letterSpacing = 7.sp
            ),
            // 使用 maxLines 参数可以帮助我们将文本限制在指定的行数之间，如果文本足够短则不会生效，如果文本超过 maxLines 所规定的行数，则会进行截断
            maxLines = 1,
            // 使用 overflow 参数可以帮助我们处理溢出的视觉效果
            overflow = TextOverflow.Ellipsis
        )

        // Text 中设置了 fillMaxWidth() 之后，我们可以指定 Text 的对齐方式
        Text(
            text = "Simplation ".repeat(15), modifier = Modifier.fillMaxWidth(),
            // TextAlign 设置的是文本的对齐方式，而不是位置方向
            // 如果需要实现 TextAlign.Right 中的方向，请使用 Modifier.align(Alignment.End)
            textAlign = TextAlign.Left
        )

        Spacer(modifier = Modifier.padding(vertical = 15.dp))

        Text(
            text = "Simplation ".repeat(10),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            // 使用 lineHeight 参数可以指定 Text 中每行的行高间距
            lineHeight = 30.sp,
            fontFamily = FontFamily.Monospace
        )

        // 文字复制
        // 默认情况下 Text 并不能进行复制等操作，需要设置 SelectionContainer 来包装 Text
        SelectionContainer {
            Text(
                text = "Simplation",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Right,
                // 使用 fontFamily 参数可以自定义字体
                fontFamily = FontFamily.Serif
            )
        }
        // 在 res 中创建 font 字体文件夹，可以直接引用相应的字体
        // fontFamily = FontFamily(Font(R.font.pingfang, FontWeight.W700))

        // LocalContentAlpha 实现文字强调
        // 将内部 Text 组件的 alpha 强调程度设置为高
        // 注意: MaterialTheme 已经默认将强调程度设置为 high
        // TODO: M3 的替换方法
        CompositionLocalProvider1(LocalContentAlpha provides ContentAlpha.high) {
            Text(text = "这里是high强调效果")
        }

        // 将内部 Text 组件的 alpha 强调程度设置为中
        CompositionLocalProvider1(LocalContentAlpha provides ContentAlpha.medium) {
            Text(text = "这里是medium强调效果")
        }

        // 将内部 Text 组件的 alpha 强调程度设置为禁用
        CompositionLocalProvider1(LocalContentAlpha provides ContentAlpha.disabled) {
            Text(text = "这里是禁用后的效果")
        }
    }

    Column {
        // 文字居中效果
        Text(text = "123")
        Text(text = "456")
        Box(
            modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            Text(text = "789")
        }
    }

}

@Composable
fun ClickText() {
    Column {
        // 获取 Context
        val context = LocalContext.current

        // 将文本当作按钮，需要添加 Modifier.clickable
        Text(
            text = "确认编辑", modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = {
                        Toast
                            .makeText(context, "你点击了此文本", Toast.LENGTH_SHORT)
                            .show()
                    }, indication = null, interactionSource = MutableInteractionSource()
                )
        )

        Spacer(modifier = Modifier.padding(vertical = 15.dp))


        // 文本超链接, 类似于用户协议
        val annotatedText = buildAnnotatedString {
            append("勾选即代表同意")

            //region 文本超链接，用户协议
            pushStringAnnotation(
                tag = "tag", annotation = "用户协议的内容，内容内容"
            )
            withStyle(
                style = SpanStyle(
                    color = Color(0xFF0E9FF2), fontWeight = FontWeight.Bold
                )
            ) {
                append("用户协议")
            }

            pop()
            //endregion
        }

        ClickableText(modifier = Modifier.fillMaxWidth(),
            text = annotatedText,
            onClick = { offset ->
                annotatedText.getStringAnnotations(
                    tag = "tag", start = offset, end = offset
                ).firstOrNull()?.let { annotation ->
                    Log.d("TAG", "你已经点到 ${annotation.item} 啦")
                }
            })
    }
}

@Composable
fun AnnotatedStringText() {
    Column(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(buildAnnotatedString {
            append("你现在所处的章节：")
            withStyle(style = SpanStyle(fontWeight = FontWeight.W900)) {
                append("Text")
            }
        })
    }
}

// 用户协议的 demo
@Composable
fun UserAgreementDemo() {
    var content by remember { mutableStateOf("") }
    val openDialog = remember { mutableStateOf(false) }
    val annotatedText = buildAnnotatedString {
        append("勾选即代表同意")
        pushStringAnnotation(
            tag = "tag", annotation = stringResource(id = R.string.agreement)
        )
        withStyle(
            style = SpanStyle(
                color = Color(0xFF0E9FF2), fontWeight = FontWeight.Bold
            )
        ) {
            append("用户协议")
        }
        pop()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 15.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        ClickableText(text = annotatedText, onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = "tag", start = offset, end = offset
            ).firstOrNull()?.let { annotation ->
                openDialog.value = true
                content = annotation.item
            }
        })
    }

    if (openDialog.value) {
        AlertDialog(onDismissRequest = {
            openDialog.value = false
        }, title = {
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = "用户协议",
                    style = MaterialTheme.typography.headlineSmall,
                )
            }
        }, text = {
            Text(content)
        }, confirmButton = {
            Button(onClick = {
                openDialog.value = false
            }) {
                Text("确认")
            }
        }, dismissButton = {
            Button(onClick = {
                openDialog.value = false
            }) {
                Text("取消")
            }
        })
    }
}


@Preview
@Composable
fun PreviewText() {
    SamplesTheme {
        TextDemo()
    }
}

@Preview
@Composable
fun PreviewClickText() {
    SamplesTheme {
        ClickText()
        AnnotatedStringText()
    }
}

@Preview
@Composable
fun PreviewUserAgreement() {
    SamplesTheme {
        UserAgreementDemo()
    }
}