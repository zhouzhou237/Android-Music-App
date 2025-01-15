package com.example.app.feature.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.app.feature.splash.SPLASH_ROUTE


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
    finishPage: () -> Unit,
    toSheetDetail: (String) -> Unit,
) {
    composable(MAIN_ROUTE) {
        MainRoute(
            finishPage = finishPage,
            toSheetDetail = toSheetDetail,
        )
    }
}