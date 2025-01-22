package com.example.app.feature.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.app.feature.splash.SPLASH_ROUTE
import com.example.app.ui.MyAppUiState


const val MAIN_ROUTE = "main"


/*
跳转页面
 */
fun NavController.navigateToMain(): Unit {
    navigate(MAIN_ROUTE){
        //不开启多个页面
        launchSingleTop = true
        //关闭splash，以及以前所有页面
        popUpTo(SPLASH_ROUTE) {
            inclusive = true
        }
    }
}


/*
配置导航
 */
fun NavGraphBuilder.mainScreen(
    appUiState: MyAppUiState,
    finishPage: () -> Unit,
    toSheetDetail: (String) -> Unit,
    toFriend: (Int) -> Unit,
    toMessage: () -> Unit,
    toScan: () -> Unit,
    toProfile: () -> Unit,
    toCode: () -> Unit,
    toLogin: () -> Unit,
    toSetting: () -> Unit,
    toAbout: () -> Unit,
    toMusicPlayer: () -> Unit,
) {
    composable(MAIN_ROUTE) {
        MainRoute(
            appUiState = appUiState,
            finishPage = finishPage,
            toSheetDetail = toSheetDetail,
            toFriend = toFriend,
            toMessage = toMessage,
            toCode = toCode,
            toProfile = toProfile,
            toLogin = toLogin,
            toScan = toScan,
            toSetting = toSetting,
            toAbout = toAbout,
            toMusicPlayer = toMusicPlayer,
        )
    }
}