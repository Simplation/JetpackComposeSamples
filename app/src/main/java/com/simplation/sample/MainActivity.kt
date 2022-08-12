package com.simplation.sample

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simplation.sample.data.Message
import com.simplation.sample.data.MsgData
import com.simplation.sample.ui.theme.GreetingCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingCardTheme {
                MessageCard(msg = Message("Jetpack Compose 博物馆", "已经开始更新了"))
            }
        }
    }
}

@Composable
fun MessageCard(msg: Message) {
    // Column 相当于 线性布局的垂直布局; Row 相当于 线性布局的水平布局
    // Box 将元素堆叠起来;

    var isExpanded by remember { mutableStateOf(false) } // 创建一个能够检测卡片是否被展开的变量
    val surfaceColor by animateColorAsState(targetValue = if (isExpanded) Color(0xFFCCCCCC) else MaterialTheme.colorScheme.surface)

    Surface(
        shape = RectangleShape,
        shadowElevation = 8.dp,
        modifier = Modifier
            .padding(all = 8.dp)
            .clickable { // 编写点击的事件内容
                isExpanded = !isExpanded // 编写点击的事件内容
            },
        color = surfaceColor
    ) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painterResource(id = R.drawable.baseline_hive_24),
                contentDescription = "profile picture", // 描述无障碍
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.padding(horizontal = 8.dp))

            Column {
                Text(
                    text = msg.author,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                Text(
                    text = msg.body, style = MaterialTheme.typography.bodyMedium,
                    // 修改 maxLines 参数，在默认情况下，只显示一行文本内容
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    // Composable 大小的动画效果
                    modifier = Modifier.animateContentSize()
                )
            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(msg = message)
        }
    }
}


//@Preview(name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark Mode")
@Composable
fun PreviewMessageCard() {
    GreetingCardTheme(darkTheme = true) {
//        MessageCard(msg = Message("Jetpack Compose 博物馆", "已经开始更新了"))
        Conversation(messages = MsgData.messages)
    }
}

