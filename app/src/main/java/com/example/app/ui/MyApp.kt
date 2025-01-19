package com.example.app.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation
import com.example.app.feature.main.mainScreen
import com.example.app.feature.main.navigateToMain
import com.example.app.feature.sheetdetail.navigateToSheetDetail
import com.example.app.feature.sheetdetail.sheetDetailScreen
import com.example.app.feature.splash.SPLASH_ROUTE
import com.example.app.feature.splash.splashScreen
import com.quick.app.feature.mediaplayer.musicPlayerScreen
import com.quick.app.feature.mediaplayer.navigateToMusicPlayer


@Composable
fun MyApp(navController : NavHostController) {

    NavHost(navController = navController, startDestination = SPLASH_ROUTE) {
        splashScreen(
            toMain = navController::navigateToMain
        )
        mainScreen(
            finishPage = navController::popBackStack,
            toSheetDetail = navController::navigateToSheetDetail
        )
        sheetDetailScreen(
            finishPage = navController::popBackStack,
            toMusicPlayer = navController::navigateToMusicPlayer
        )
        musicPlayerScreen(
            finishPage = navController::popBackStack,
        )
    }

}