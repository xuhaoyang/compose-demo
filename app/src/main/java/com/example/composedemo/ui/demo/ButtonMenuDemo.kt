package com.example.composedemo.ui.demo

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.*
import com.example.composedemo.R
import com.example.composedemo.titleLiveData
import kotlinx.coroutines.delay

/**
 * User: xuhaoyang
 * mail: xuhaoyang3x@gmail.com
 * Date: 2021/10/25
 * Time: 3:51 下午
 * Description: No Description
 */
@Composable
fun ButtonMenuDialogPage() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            ButtonDemo()
            ButtonDemoByInteractionSource()
            IconButtonDemo()
            FloatingActionButtonDemo()
            ExtendedFloatingActionButtonDemo()
            IconToggleButtonDemo()
            RadioButtonDemo()
            TextButtonDemo()
            DropdownMenuDemo()
            PopupDemo()
            Box(
                Modifier
                    .size(100.dp)
                    .background(Color.Gray)
            )
            DialogDemo()
            AlertDialogDemo()
        }
    }
    LaunchedEffect(Unit) {
        delay(500L)
        titleLiveData.value = "Compose Button Menu"
    }


}

@Composable
fun ButtonDemo() {
    val context = LocalContext.current
    Column(modifier = Modifier.padding(10.dp, 10.dp)) {
        Button(
            onClick = {
                Toast.makeText(context, "点击了登录", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text(text = stringResource(id = R.string.login))
        }
        Spacer(modifier = Modifier.size(8.dp))
        Button(
            onClick = {
                Toast.makeText(context, "点击了登录", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .size(80.dp, 40.dp)
                .clip(RoundedCornerShape(20.dp)),
            enabled = true,
            border = BorderStroke(1.dp, color = Color.Black)
        ) {
            Text(text = stringResource(id = R.string.login))
        }
    }

}

@Composable
fun ButtonDemoByInteractionSource() {
    val context = LocalContext.current
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val pressState = interactionSource.collectIsPressedAsState()
    val borderColor = if (pressState.value) Color.Green else Color.Black

    Column(modifier = Modifier.padding(10.dp, 10.dp)) {
        Button(
            onClick = {
                Toast.makeText(context, "点击了登录", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .size(80.dp, 40.dp)
                .clip(RoundedCornerShape(20.dp)),
            enabled = true,
            border = BorderStroke(1.dp, color = borderColor),
            interactionSource = interactionSource,
            elevation = ButtonDefaults.elevation(2.dp, 8.dp, 0.dp),
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Red,
                contentColor = Color.Yellow,
                disabledBackgroundColor = Color.DarkGray,
                disabledContentColor = Color.Black
            ),
            contentPadding = ButtonDefaults.ContentPadding
        ) {
            Text(text = stringResource(id = R.string.login))
        }
    }
}

@Composable
fun IconButtonDemo() {
    val context = LocalContext.current
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val pressState = interactionSource.collectIsPressedAsState()
    val pressText = if (pressState.value) "减少" else "添加"
    Column(modifier = Modifier.padding(10.dp, 10.dp)) {
        IconButton(
            onClick = {
                Toast.makeText(context, "点击了添加", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .size(80.dp, 40.dp)
                .clip(RoundedCornerShape(20)),
            enabled = true,
            interactionSource = interactionSource
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "添加的按钮", tint = Color.Red)
                Text(text = pressText, fontSize = 8.sp)
            }
        }
    }
}

@Composable
fun FloatingActionButtonDemo() {
    val context = LocalContext.current
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val pressState = interactionSource.collectIsPressedAsState()
    val pressText = if (pressState.value) "减少" else "添加"
    Column(modifier = Modifier.padding(10.dp, 10.dp)) {
        FloatingActionButton(
            onClick = {
                Toast.makeText(context, "点击了按钮", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.size(80.dp),
            interactionSource = interactionSource,
            shape = RoundedCornerShape(25.dp),
            backgroundColor = Color.Green,
            contentColor = Color.Blue,
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 8.dp,
                pressedElevation = 10.dp
            )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add", tint = Color.Red)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "按钮", fontSize = 12.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun ExtendedFloatingActionButtonDemo() {
    val context = LocalContext.current
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val pressState = interactionSource.collectIsPressedAsState()
    val pressText = if (pressState.value) "减少" else "添加"
    Column(modifier = Modifier.padding(10.dp, 10.dp)) {
        ExtendedFloatingActionButton(text = {
            Text(
                text = pressText,
                fontSize = 12.sp,
                color = Color.White
            )
        }, onClick = {
            Toast.makeText(context, "点击了按钮", Toast.LENGTH_SHORT).show()
        },
//            modifier = Modifier.size(50.dp),
            icon = {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add", tint = Color.Red)
            },
            interactionSource = interactionSource,
            shape = RoundedCornerShape(25.dp),
            backgroundColor = Color.Green,
            contentColor = Color.Blue,
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 8.dp,
                pressedElevation = 10.dp
            )
            //通过FloatingActionButtonDefaults.elevation(defaultElevation,pressedElevation)设置，第一个参数是默认阴影，第二个是按下时候的阴影
        )
    }

}

@Composable
fun IconToggleButtonDemo() {
    val context = LocalContext.current
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val pressState = interactionSource.collectIsPressedAsState()
    var isCheck = remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.padding(10.dp, 10.dp)) {
        IconToggleButton(
            checked = isCheck.value,
            onCheckedChange = {
                isCheck.value = it
            },
            modifier = Modifier.size(50.dp),
            enabled = true,
            interactionSource = interactionSource
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "点赞图标",
                tint = if (isCheck.value || pressState.value) Color.Red else Color.Black
            )
        }
    }

}

@Composable
fun RadioButtonDemo() {
    val context = LocalContext.current
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val pressState = interactionSource.collectIsPressedAsState()
    var isCheck = remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.padding(10.dp, 10.dp)) {
        RadioButton(
            selected = isCheck.value,
            onClick = {
                isCheck.value = !isCheck.value
            },
            modifier = Modifier.size(50.dp),
            enabled = true,
            interactionSource = interactionSource,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Red,
                unselectedColor = Color.Black,
                disabledColor = Color.Gray
            )
        )
    }

}

@Composable
fun TextButtonDemo() {
    val context = LocalContext.current
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val pressState = interactionSource.collectIsPressedAsState()
    Column(modifier = Modifier.padding(10.dp, 10.dp)) {
        TextButton(
            onClick = {
                Toast.makeText(context, "点击了登录", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .size(80.dp, 40.dp)
                .clip(RoundedCornerShape(20)),
            enabled = true,
            interactionSource = interactionSource,
            elevation = ButtonDefaults.elevation(2.dp, 8.dp, 0.dp),
            shape = RoundedCornerShape(20),
            border = BorderStroke(1.dp, if (pressState.value) Color.Red else Color.Yellow),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Blue,
                contentColor = Color.White,
                disabledBackgroundColor = Color.Gray,
                disabledContentColor = Color.Black
            ),
            contentPadding = ButtonDefaults.ContentPadding
        ) {
            Text(text = stringResource(id = R.string.login), fontSize = 14.sp)
        }
    }
}

@Composable
fun DropdownMenuDemo() {
    val expandState = remember {
        mutableStateOf(false)
    }
    Column() {
        Button(
            onClick = {
                expandState.value = true
            }) {
            Text(text = "打开 DropdownMenu")
        }
        DropdownMenu(
            expanded = expandState.value,
            onDismissRequest = {
                Log.e("ccm", "执行了onDismissRequest")
                expandState.value = false
            },
            offset = DpOffset(10.dp, 10.dp),
            properties = PopupProperties()
        ) {
            DropdownMenuItemDemo(expandState, Icons.Filled.Favorite, "收藏")
            DropdownMenuItemDemo(expandState, Icons.Filled.Edit, "编辑")
            DropdownMenuItemDemo(expandState, Icons.Filled.Delete, "删除")
        }
    }
}


@Composable
fun DropdownMenuItemDemo(state: MutableState<Boolean>, icon: ImageVector, text: String) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressState = interactionSource.collectIsPressedAsState()
    val focusState = interactionSource.collectIsFocusedAsState()
    DropdownMenuItem(
        onClick = {
            state.value = false
        },
        enabled = true,
        interactionSource = interactionSource
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = if (pressState.value || focusState.value) Color.Red else Color.Black
        )
        Text(
            text = text,
            modifier = Modifier.padding(start = 10.dp),
            color = if (pressState.value || focusState.value) Color.Red else Color.Black
        )
    }
}

@Composable
fun PopupDemo() {
    val expandState = remember {
        mutableStateOf(false)
    }
    Column(Modifier.padding(10.dp, 8.dp)) {
        Button(
            onClick = {
                expandState.value = true
            }) {
            Text(text = "打开 PopupDemo")
        }
        if (expandState.value) {
            Popup(
                alignment = Alignment.TopStart,
                onDismissRequest = {
                    Log.e("ccm", "执行了onDismissRequest")
                    expandState.value = false
                },
                offset = IntOffset(10, 140),
            ) {
                Column(
                    modifier = Modifier
                        .width(IntrinsicSize.Min)
                        .shadow(
                            elevation = 2.dp, shape = RoundedCornerShape(3.dp)
                        )
                        .background(Color.White, shape = RoundedCornerShape(3.dp))
                ) {
                    DropdownMenuItemDemo(expandState, Icons.Filled.Favorite, "收藏")
                    DropdownMenuItemDemo(expandState, Icons.Filled.Edit, "编辑")
                    DropdownMenuItemDemo(expandState, Icons.Filled.Delete, "删除")
                }
            }
        }
    }
}


@Composable
fun DialogDemo() {
    val state = remember {
        mutableStateOf(false)
    }
    Box(Modifier.padding(10.dp, 8.dp)) {
        Button(onClick = { state.value = true }) {
            Text(text = "打开 Dialog")
        }
    }
    if (state.value) {
        Dialog(
            onDismissRequest = {
                Log.e("ccm", "====onDismiss=====")
                state.value = false
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                securePolicy = SecureFlagPolicy.SecureOff
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 15.dp)
                    .background(
                        Color.White, shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "对话框标题",
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 10.dp),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "对话框内容,对话框内容,对话框内容,对话框内容,对话框内容,对话框内容",
                    modifier = Modifier.padding(horizontal = 10.dp),
                    lineHeight = 20.sp,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Divider(modifier = Modifier.height(0.5.dp))
                Row() {
                    Button(
                        onClick = {
                            state.value = false
                        },
                        modifier = Modifier.weight(1f, true),
                        shape = RoundedCornerShape(bottomStart = 8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    ) {
                        Text(text = "取消")
                    }
                    Button(
                        onClick = {
                        },
                        modifier = Modifier.weight(1f, true),
                        shape = RoundedCornerShape(bottomEnd = 8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    ) {
                        Text(text = "确定")
                    }
                }
            }
        }
    }
}

@Composable
fun AlertDialogDemo() {
    val state = remember {
        mutableStateOf(false)
    }
    Box(Modifier.padding(10.dp, 8.dp)) {
        Button(onClick = { state.value = true }) {
            Text(text = "打开 AlertDialog")
        }
    }
    if (state.value) {
        AlertDialog(
            onDismissRequest = { state.value = false },
            buttons = {
                Row() {
                    Button(
                        onClick = {
                            state.value = false
                        },
                        modifier = Modifier.weight(1f, true),
                        shape = RoundedCornerShape(bottomStart = 8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    ) {
                        Text(text = "取消")
                    }
                    Button(
                        onClick = {
                        },
                        modifier = Modifier.weight(1f, true),
                        shape = RoundedCornerShape(bottomEnd = 8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    ) {
                        Text(text = "确定")
                    }
                }
            },
            title = {
                Text(text = "对话框标题")
            },
            text = {
                Text(text = "对话框内容对话框内容")
            },
            shape = RoundedCornerShape(8.dp),
            backgroundColor = Color.White,
            contentColor = Color.Black,
            properties = DialogProperties()
        )
    }
}
