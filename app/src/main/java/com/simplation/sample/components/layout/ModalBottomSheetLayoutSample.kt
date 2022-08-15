package com.simplation.sample.components.layout

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simplation.sample.ui.theme.SamplesTheme
import kotlinx.coroutines.launch

/**
 * ModalBottomSheetLayout 呈现了一系列的选择，同时阻止了与屏幕其他部分的互动。
 * ModalSheetLayout 总共有三种状态：Hidden、HalfExpanded、Expanded
 *
 * hide 和 show 的时候可以使用 animateTo 添加动画
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ModalBottomSheetLayoutDemo() {
    val state = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetContent = {
            Column {
                ListItem(text = { Text(text = "点击选择分享到~？") })

                ListItem(text = { Text(text = "Email") }, icon = {
                    Surface(color = Color(0xFF181717), shape = CircleShape) {
                        Icon(
                            imageVector = Icons.Filled.Email,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }, modifier = Modifier.clickable { })

                ListItem(text = { Text(text = "Call") }, icon = {
                    Surface(color = Color(0xFF181717), shape = CircleShape) {
                        Icon(
                            imageVector = Icons.Filled.Call,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }, modifier = Modifier.clickable { })
            }
        }, sheetState = state
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                scope.launch {
                    // state.show()
                    state.animateTo(ModalBottomSheetValue.Expanded, tween(1000))
                }
            }) {
                Text(text = "点击展开...")
            }
        }
    }

    BackHandler(
        enabled = (state.currentValue == ModalBottomSheetValue.HalfExpanded || state.currentValue == ModalBottomSheetValue.Expanded),
        onBack = {
            scope.launch {
                //state.hide()
                state.animateTo(ModalBottomSheetValue.Hidden, tween(1000))  // 添加动画
            }
        })
}

@Preview
@Composable
fun PreviewModalBottomSheetLayout() {
    SamplesTheme {
        ModalBottomSheetLayoutDemo()
    }
}