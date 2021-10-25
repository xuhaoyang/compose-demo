package com.example.composedemo.ui.demo

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
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
fun ButtonMenuPage() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
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
        }
    }
    LaunchedEffect(Unit){
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
fun DropdownMenuDemo(){
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
                Log.e("ccm","执行了onDismissRequest")
                expandState.value = false
            },
            offset = DpOffset(10.dp,10.dp),
            properties = PopupProperties()
        ) {
            DropdownMenuItemDemo(expandState,Icons.Filled.Favorite,"收藏")
            DropdownMenuItemDemo(expandState,Icons.Filled.Edit,"编辑")
            DropdownMenuItemDemo(expandState,Icons.Filled.Delete,"删除")
        }
    }
}


@Composable
fun DropdownMenuItemDemo(state:MutableState<Boolean>, icon: ImageVector, text:String){
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
        Icon(imageVector = icon, contentDescription = text,tint = if(pressState.value || focusState.value) Color.Red else Color.Black)
        Text(text = text,modifier = Modifier.padding(start = 10.dp),color = if(pressState.value || focusState.value) Color.Red else Color.Black)
    }
}

