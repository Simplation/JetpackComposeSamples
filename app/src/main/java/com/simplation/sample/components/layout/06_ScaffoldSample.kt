package com.simplation.sample.components.layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simplation.sample.R
import com.simplation.sample.ui.theme.SamplesTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Scaffold 实现了 Material Design 的基本视图界面结构, 如侧边应用栏、底部导航栏、导航栏等效果
 * Scaffold 中的 topBar、bottomBar 参数仅仅只是帮助我们定位布局的位置
 * 可以经常看到它们和 TopAppBar、BottomNavigation、BottomAppBar 来一起搭配使用
 */
class ScaffoldSample : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SamplesTheme {
                AppScaffold()
            }
        }
    }
}


@Composable
fun AppScaffold() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    var selectedItem by remember { mutableStateOf(0) }

    val items = listOf("主页", "喜欢", "设置")

    Scaffold(scaffoldState = scaffoldState, topBar = {
        TopAppBar(navigationIcon = {
            androidx.compose.material3.IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
            }
        }, title = { Text(text = "Drawer 工坊") })
    }, bottomBar = {
        BottomNavigation {
            items.forEachIndexed { index, item ->
                BottomNavigationItem(icon = {
                    when (index) {
                        0 -> Icon(
                            imageVector = Icons.Filled.Home, contentDescription = null
                        )
                        1 -> Icon(
                            imageVector = Icons.Filled.Favorite, contentDescription = null
                        )
                        else -> Icon(
                            imageVector = Icons.Filled.Settings, contentDescription = null
                        )
                    }
                },
                    label = { Text(text = item) },
                    selected = selectedItem == index,
                    onClick = { selectedItem = index })
            }
        }
    }, drawerContent = {
        AppDrawerContent(scaffoldState, scope)
    }) {
        /*
           when(selectedItem) {
                0 -> { Home() }
                1 -> { Favorite() }
                else -> { Settings() }
           }
         */
        // Home(), Favorite(), Settings() 都是单独的 Composable 函数

        AppContent(item = items[selectedItem])
    }
}

@Composable
fun AppContent(item: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "当前页面为$item")
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppDrawerContent(scaffoldState: ScaffoldState, scope: CoroutineScope) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null
        )

        Column(modifier = Modifier.padding(15.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_image),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(60.dp)
                    .border(
                        BorderStroke(1.dp, Color.Gray), CircleShape
                    )
            )

            Spacer(modifier = Modifier.padding(vertical = 10.dp))

            Text(text = "游客001", style = MaterialTheme.typography.h2)
        }
    }

    ListItem(
        icon = {
            Icon(imageVector = Icons.Filled.Home, contentDescription = null)
        },
        modifier = Modifier.clickable { }
    ) {
        Text(text = "首页")
    }
    
    Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.BottomStart) {
        androidx.compose.material3.TextButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.Settings, null)
            Text("设置")
        }
    }

    // 编写逻辑
    // 如果 drawer 已经展开了，那么点击返回键收起而不是直接退出 app

    BackHandler(enabled = scaffoldState.drawerState.isOpen) {
        scope.launch {
            scaffoldState.drawerState.close()
        }
    }
}

@Preview
@Composable
fun PreviewScaffolds() {
    SamplesTheme {
        AppScaffold()
    }
}