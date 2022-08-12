package com.simplation.sample.components.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.simplation.sample.ui.theme.SamplesTheme

class AlertDialogSample : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SamplesTheme {
                AlertDialogDemo()
            }
        }
    }
}

@Composable
fun DialogDemo() {
    var flag by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = { flag = true }) {
            Text(text = "弹出 Dialog")
        }
    }

    if (flag) {
        Dialog(onDismissRequest = { flag = false }) {
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    LinearProgressIndicator()
                    Text(
                        text = "加载中..."
                    )
                }
            }
        }
    }
}

@Composable
fun AlertDialogDemo() {
    val openDialog = remember {
        mutableStateOf(true)
    }

    if (openDialog.value) {
        AlertDialog(onDismissRequest = {
            // 当用户点击对话框以外的地方或者按下系统返回键将会执行的代码
            openDialog.value = false
        }, title = {
            Text(
                text = "开启位置服务",
                fontWeight = FontWeight.W700,
                style = MaterialTheme.typography.headlineMedium
            )
        }, text = {
            Text(
                text = "这将意味着，我们会给您提供精准的位置服务，并且您将接受关于您订阅的位置信息", fontSize = 16.sp
            )
        }, confirmButton = {
            TextButton(onClick = { openDialog.value = false }) {
                Text(
                    text = "确认",
                    fontWeight = FontWeight.W700,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }, dismissButton = {
            TextButton(onClick = { openDialog.value = false }) {
                Text(
                    "取消", fontWeight = FontWeight.W700, style = MaterialTheme.typography.labelMedium
                )
            }
        })
    }
}

@Preview
@Composable
fun PreviewShowDialog() {
    SamplesTheme {
        DialogDemo()
    }
}

@Preview
@Composable
fun PreviewShowAlertDialog() {
    SamplesTheme {
        AlertDialogDemo()
    }
}