package com.simplation.sample.components.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.simplation.sample.ui.theme.GreetingCardTheme

class ImageSample : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GreetingCardTheme {
                ImageDemo()
                CoilImageDemo()
            }
        }
    }
}

@Composable
fun ImageDemo() {
    // 加载一张图片
    // Image(painter = painterResource(id = R.drawable.ic_image), contentDescription = null)

    // 使用 Modifier.size() 来设置图片大小
    // Image(
    //    painter = painterResource(id = R.drawable.ic_image),
    //    contentDescription = null,
    //    modifier = Modifier.size(350.dp)
    // )

    // 使用 Surface 来设置形状
    Surface(shape = CircleShape, border = BorderStroke(width = 5.dp, color = Color.Gray)) {
        Image(
            painter = painterResource(id = R.drawable.ic_image),
            contentDescription = null,
            modifier = Modifier.size(350.dp),
            // Image 中源码的 contentScale 参数默认是 ContentScale.Fit
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun CoilDemo() {
    Image(
        painter = rememberImagePainter(data = "https://picsum.photos/300/300"),
        contentDescription = null
    )

    // 使用 Coil 加载 SVG
    // https://compose-museum.gitee.io/hello-compose/elements/image/
}

@Composable
fun CoilImageDemo() {
    var flag by remember {
        mutableStateOf(true)
    }

    /*CoilImage(
        imageModel = "https://coil-kt.github.io/coil/images/coil_logo_black.svg",
        contentDescription = null,
        modifier = Modifier
            .size(size)
            .clickable(
                onClick = {
                    flag = !flag
                },
                indication = null,
                interactionSource = MutableInteractionSource()
            ),
        imageLoader = imageLoader
    )*/
}

@Preview
@Composable
fun PreviewCoilImageDemo() {
    GreetingCardTheme {
        CoilImageDemo()
    }
}

@Preview
@Composable
fun PreviewCoilDemo() {
    GreetingCardTheme {
        CoilDemo()
    }
}

@Preview
@Composable
fun PreviewImage() {
    GreetingCardTheme {
        ImageDemo()
    }
}