package com.simplation.sample.components.layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.simplation.sample.ui.theme.SamplesTheme

// TODO: 稍后完善...
class TopAppBarSample : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SamplesTheme {
                TopAppBarDemo()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarDemo() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("主页")
                },
                navigationIcon = {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                },
                // 设置 actions 参数，它会将里面的内容以 Row 的方式来排列
                actions = {
                    IconButton(
                        onClick = { } // do something
                    ) {
                        Icon(Icons.Filled.Search, null)
                    }
                    IconButton(
                        onClick = { } // do something
                    ) {
                        Icon(Icons.Filled.MoreVert, null)
                    }
                }
            )
        },
    ) {

    }
}

@Preview
@Composable
fun PreviewTopAppBar() {
    SamplesTheme {
        TopAppBarDemo()
    }
}