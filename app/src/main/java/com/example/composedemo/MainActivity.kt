package com.example.composedemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.composedemo.MainActivity.Companion.TAG
import com.example.composedemo.list.ListPage
import com.example.composedemo.navigation.Screen
import com.example.composedemo.ui.demo.*
import com.example.composedemo.ui.public.FunctionList
import com.example.composedemo.ui.public.Page
import com.example.composedemo.ui.theme.CustomThemeManager
import com.example.composedemo.ui.theme.ThemeType
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

val titleLiveData = MutableLiveData<String>()
val themeTypeState = mutableStateOf(ThemeType.Default)
val darkThemeState = mutableStateOf(false)

class MainActivity : ComponentActivity() {
    @ExperimentalTextApi
    @ExperimentalUnitApi
    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val rememberTitle: String by titleLiveData.observeAsState("Compose Demo")
            val themType: ThemeType by themeTypeState
            val isDarkTheme: Boolean by darkThemeState
            val wrappedColor = CustomThemeManager.getWrappedColor(themType)
            window.statusBarColor = if (isDarkTheme) {
                Color(0xFF181818)
            } else {
                wrappedColor.lightColors.primary
            }.toArgb()
            Page(rememberTitle, themType, isDarkTheme) {
                Content()
            }
        }
    }
}


@ExperimentalTextApi
@ExperimentalUnitApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun Content() {
//    val navController = rememberNavController()
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.main
    ) {
        composable(
            Screen.main,
            enterTransition = { initial, target ->
                Log.i(TAG, "main enterTransition: ${initial.destination.route}")
                when (initial.destination.route) {
                    Screen.theme -> {
                        slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(700))
                    }
                    else -> null
                }
            },
            exitTransition = { _, target ->
                Log.i(TAG, "main exitTransition: ${target.destination.route}")
                when (target.destination.route) {
                    Screen.theme ->
                        slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(700))
                    else -> null
                }
            },
            popEnterTransition = { initial, _ ->
                Log.i(TAG, "main popEnterTransition: ${initial.destination.route}")
                when (initial.destination.route) {
                    Screen.theme ->
                        slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(700))
                    else -> null
                }
            },
            popExitTransition = { _, target ->
                Log.i(TAG, "main popExitTransition: ${target.destination.route}")
                when (target.destination.route) {
                    Screen.theme ->
                        slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(700))
                    else -> null
                }
            }
        ) {
            Main(navController)
        }
        composable(
            Screen.animation,
            enterTransition = { initial, _ ->
                Log.i(TAG, "enterTransition: ${initial.destination.route}")
                when (initial.destination.route) {
                    Screen.main ->
                        slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(700))
                    else -> null
                }
            },
            exitTransition = { _, target ->
                Log.i(TAG, "exitTransition: ${target.destination.route}")
                when (target.destination.route) {
                    Screen.main ->
                        slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(700))
                    else -> null
                }
            },
            popEnterTransition = { initial, _ ->
                Log.i(TAG, "popEnterTransition: ${initial.destination.route}")
                when (initial.destination.route) {
                    Screen.main ->
                        slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(700))
                    else -> null
                }
            },
            popExitTransition = { _, target ->
                Log.i(TAG, "popExitTransition: ${target.destination.route}")
                when (target.destination.route) {
                    Screen.main ->
                        slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(700))
                    else -> null
                }
            }
        ) { AnimationPage() }
        composable(Screen.gesture) { GesturePage() }
        composable(Screen.image) { ImagePage() }
        composable(Screen.canvas) { CanvasPage() }
        composable(Screen.layout) { LayoutPage() }
        composable(Screen.custom_layout) { CustomLayoutPage() }
        composable(Screen.List.main) { ListPage() }
        composable(Screen.text) { TextPage() }
        composable(Screen.theme) { ThemePage() }
    }
}

//解决Nav过度动画
//https://medium.com/androiddevelopers/animations-in-navigation-compose-36d48870776b
@ExperimentalAnimationApi
@Composable
fun EnterAnimation(content: @Composable () -> Unit) {
    var state by remember { mutableStateOf(false) }
    AnimatedVisibility(
        visible = state,
        modifier = Modifier,
        enter = fadeIn(animationSpec = tween(durationMillis = 500)),
        exit = fadeOut(animationSpec = tween(durationMillis = 500)),
    ) {
        content()
    }
    LaunchedEffect(Unit) {
        state = true
    }
    DisposableEffect(key1 = Unit) {
        onDispose {
            state = false
        }
    }
}


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun Main(navController: NavHostController) {
    titleLiveData.value = "Compose Demo"
    val viewModel: MainViewModel = viewModel()
    FunctionList(functions = viewModel.functions, navController = navController)
}