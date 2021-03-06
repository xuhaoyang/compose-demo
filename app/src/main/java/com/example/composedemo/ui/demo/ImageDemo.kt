package com.example.composedemo.ui.demo

import android.graphics.Paint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.applyCanvas
import androidx.core.graphics.createBitmap
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.request.ImageResult
import com.example.composedemo.R
import com.example.composedemo.titleLiveData
import com.example.composedemo.ui.demo.ImageObject.TAG

object ImageObject {
    const val TAG = "Image"
}

@Composable
fun ImagePage() {
    titleLiveData.value = "Compose Image"
    ImageContent()
}

@Composable
fun ImageContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        ImageDemo()
    }
}

@Composable
fun ImageDemo() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        LoadDrawableDemo()
        LoadBitmapDemo()
        RoundCornerDemo()
        CircleDemo()
        ObserveStateDemo()
        LoadUrlDemo()
        ColorFilterDemo()
        TintDemo()
        ContentScaleDemo()
    }
}


@Composable
fun UrlImage(contentScale: ContentScale?) {
    Image(
        painter = rememberImagePainter(
            data = "https://pic3.zhimg.com/v2-77bbb941f260b90f0193ef73d3f2b9e4_1440w.jpg?source=172ae18b",
            builder = {
                crossfade(true)
            }
        ), contentDescription = null,
        modifier = Modifier
            .size(200.dp, 300.dp)
            .background(MaterialTheme.colors.onBackground),
        contentScale = contentScale ?: ContentScale.Fit
    )
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun LoadDrawableDemo() {
    Text(text = "??????drawable")
    Image(painter = painterResource(id = R.drawable.logo), contentDescription = null)
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun LoadBitmapDemo() {
    Text(text = "??????bitmap")
    val bitmap = createBitmap(100, 100)
    bitmap.applyCanvas {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = MaterialTheme.colors.onBackground.toArgb()
        drawCircle(50f, 50f, 50f, paint)
    }
    Image(bitmap = bitmap.asImageBitmap(), contentDescription = null)
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun RoundCornerDemo() {
    Text(text = "????????????")
    androidx.compose.material.Surface(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        Image(
            painter = rememberImagePainter(
                data = "https://pic3.zhimg.com/v2-77bbb941f260b90f0193ef73d3f2b9e4_1440w.jpg?source=172ae18b",
                builder = {
                    crossfade(true)
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .background(MaterialTheme.colors.onBackground),
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun CircleDemo() {
    Text(text = "???")
    androidx.compose.material.Surface(
        shape = RoundedCornerShape(percent = 50),
        modifier = Modifier.padding(10.dp)
    ) {
        Image(
            painter = rememberImagePainter(
                data = "https://pic3.zhimg.com/v2-77bbb941f260b90f0193ef73d3f2b9e4_1440w.jpg?source=172ae18b",
                builder = {
                    crossfade(true)
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .background(MaterialTheme.colors.onBackground),
            contentScale = ContentScale.Crop
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun ObserveStateDemo() {
    Text(text = "??????????????????")
    var loading by remember {
        mutableStateOf(true)
    }
    val painter =
        rememberImagePainter(
            data = "https://macjpeg.macsc.com/macdown/pic/202009/03113634_e7f0bb805b.jpeg",
            builder = {
                crossfade(true)
                listener(object : ImageRequest.Listener {
                    override fun onStart(request: ImageRequest) {
                        super.onStart(request)
                        Log.i(TAG, "onStart: ${request.data}")
                    }

                    override fun onSuccess(request: ImageRequest, metadata: ImageResult.Metadata) {
                        super.onSuccess(request, metadata)
                        Log.i(TAG, "onSuccess: ${request.data}")
                        loading = false
                    }
//
////                    override fun onCancel(request: ImageRequest) {
////                        super.onCancel(request)
////                        Log.i(TAG, "onCancel: ")
////                        loading = false
////                    }
//
//                    override fun onError(request: ImageRequest, throwable: Throwable) {
//                        super.onError(request, throwable)
//                        Log.i(TAG, "onError: ")
//                        loading = false
//                    }
                })


            }
        )
    Box {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.FillWidth
        )

        if (loading) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun LoadUrlDemo() {
    Text(text = "??????url")
    Image(
        painter = rememberImagePainter(
            data = "https://macjpeg.macsc.com/macdown/pic/202009/03113634_e7f0bb805b.jpeg",
            builder = {
                crossfade(true)
            }
        ),
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        contentDescription = null,
    )
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun ColorFilterDemo() {
    Text(text = "colorFilter")
    Image(
        painter = rememberImagePainter(
            data = "https://macjpeg.macsc.com/macdown/pic/202009/03113634_e7f0bb805b.jpeg",
            builder = {
                crossfade(true)
            }
        ),
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        colorFilter = ColorFilter.colorMatrix(
            ColorMatrix(
                floatArrayOf(
                    -1f, 0f, 0f, 0f, 255f,
                    0f, -1f, 0f, 0f, 255f,
                    0f, 0f, -1f, 0f, 255f,
                    0f, 0f, 0f, 1f, 0f
                )
            )
        ),
        contentDescription = null,
    )
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun TintDemo() {
    Text(text = "tint")
    Image(
        painter = painterResource(id = R.drawable.logo), contentDescription = null,
        colorFilter = ColorFilter.tint(androidx.compose.ui.graphics.Color.LightGray)
    )
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun ContentScaleDemo() {
    Text(text = "ContentScale.Crop")
    UrlImage(contentScale = ContentScale.Crop)

    Text(text = "ContentScale.Fit")
    UrlImage(contentScale = ContentScale.Fit)

    Text(text = "ContentScale.FillBounds")
    UrlImage(contentScale = ContentScale.FillBounds)

    Text(text = "ContentScale.FillHeight")
    UrlImage(contentScale = ContentScale.FillHeight)

    Text(text = "ContentScale.FillWidth")
    UrlImage(contentScale = ContentScale.FillWidth)

    Text(text = "ContentScale.Inside")
    UrlImage(contentScale = ContentScale.Inside)

    Text(text = "ContentScale.None")
    UrlImage(contentScale = ContentScale.None)
}