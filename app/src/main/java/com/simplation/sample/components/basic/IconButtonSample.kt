package com.simplation.sample.components.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simplation.sample.ui.theme.GreetingCardTheme

class IconButtonSample : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GreetingCardTheme {
                // IconButton 生成一个可点击的图标按钮
                IconButtonDemo()
            }
        }
    }
}

@Composable
fun MyIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
) {
    // 这也是源码的一部分
    val IconButtonSizeModifier = Modifier.size(48.dp)

    Box(
        modifier = modifier
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = null
            )
            .then(IconButtonSizeModifier), contentAlignment = Alignment.Center
    ) { content() }
}

@Composable
fun ShowMyIconButton() {
    MyIconButton(onClick = { /*TODO*/ }) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.Done, contentDescription = null)
        }
    }
}

@Composable
fun IconButtonDemo() {
    Row {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
        }

        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
        }

        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.Done, contentDescription = null)
        }
    }
}

@Preview
@Composable
fun PreviewIconButton() {
    GreetingCardTheme {
        IconButtonDemo()
    }
}

@Composable
@Preview
fun PreviewMyIconButton() {
    GreetingCardTheme {
        ShowMyIconButton()
    }
}
