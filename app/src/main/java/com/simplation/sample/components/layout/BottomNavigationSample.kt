package com.simplation.sample.components.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simplation.sample.ui.theme.SamplesTheme

class BottomNavigationSample {}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldDemo() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Favorites", "Setting")

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Home") }, navigationIcon = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
            }
        })
    }, bottomBar = {
        BottomNavigation {
            items.forEachIndexed { index, item ->
                BottomNavigationItem(icon = {
                    when (index) {
                        0 -> Icon(imageVector = Icons.Filled.Home, contentDescription = null)
                        1 -> Icon(
                            imageVector = Icons.Filled.Favorite, contentDescription = null
                        )
                        else -> Icon(
                            imageVector = Icons.Filled.Settings, contentDescription = null
                        )
                    }
                }, label = { Text(item) }, selected = selectedItem == index, onClick = {
                    selectedItem = index
                })
            }
        }
    }) {

    }
}

// 自定义 BottomNavigation 栏
@Composable
fun CustomBottomNavigation() {
    var selectedItem by remember { mutableStateOf(0) }

    BottomNavigation(backgroundColor = Color.White) {
        for (index in 0..2) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable(
                        onClick = {
                            selectedItem = index
                        }, indication = null, interactionSource = MutableInteractionSource()
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NavigationIcon(index = index, selectedItem = selectedItem)
                Spacer(modifier = Modifier.padding(top = 2.dp))
                AnimatedVisibility(visible = index == selectedItem) {
                    Surface(
                        shape = CircleShape,
                        modifier = Modifier.size(5.dp),
                        color = Color(0xFF252527)
                    ) {

                    }
                }
            }
        }
    }
}

@Composable
fun NavigationIcon(index: Int, selectedItem: Int) {
    val alpha = if (selectedItem != index) 0.5f else 1f

    CompositionLocalProvider(LocalContentAlpha provides alpha) {
        when(index) {
            0 -> Icon(imageVector = Icons.Filled.Home, contentDescription = null)
            1 -> Icon(imageVector = Icons.Filled.Call, contentDescription = null)
            else -> Icon(imageVector = Icons.Filled.Settings, contentDescription = null)
        }
    }
}

@Preview
@Composable
fun PreviewScaffold() {
    SamplesTheme {
        ScaffoldDemo()
    }
}

@Preview
@Composable
fun PreviewCustomBottomNavigation() {
    SamplesTheme {
        CustomBottomNavigation()
    }
}