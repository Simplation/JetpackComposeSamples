package com.simplation.sample.components.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.simplation.greetingcard.R
import com.simplation.sample.ui.theme.GreetingCardTheme

class TextSample : ComponentActivity() {

    // https://compose-museum.gitee.io/hello-compose/elements/text/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GreetingCardTheme {
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
            text = stringResource(id = R.string.content),
            style = TextStyle(
                fontWeight = FontWeight.W900,
                fontSize = 20.sp,
                letterSpacing = 7.sp
            ),
            maxLines = 1
        )
    }

}

@Preview
@Composable
fun PreviewText() {
    GreetingCardTheme {
        TextDemo()
    }
}
