package com.example.app.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation
import com.example.app.core.data.repository.UserDataRepository
import com.example.app.feature.login.loginScreen
import com.example.app.feature.login.navigateToLogin
import com.example.app.feature.loginhome.finishAllLoginPages
import com.example.app.feature.loginhome.loginHomeScreen
import com.example.app.feature.loginhome.navigateToLoginHome
import com.example.app.feature.main.mainScreen
import com.example.app.feature.main.navigateToMain
import com.example.app.feature.sheetdetail.navigateToSheetDetail
import com.example.app.feature.sheetdetail.sheetDetailScreen
import com.example.app.feature.splash.SPLASH_ROUTE
import com.example.app.feature.splash.splashScreen
import com.quick.app.feature.mediaplayer.musicPlayerScreen
import com.quick.app.feature.mediaplayer.navigateToMusicPlayer


@Composable
fun MyApp(
    navController : NavHostController,
    userDataRepository: UserDataRepository,
    appUiState: MyAppUiState = rememberMyAppUiState(userDataRepository = userDataRepository)
) {

    NavHost(navController = navController, startDestination = SPLASH_ROUTE) {
        splashScreen(
            toMain = navController::navigateToMain
        )
        mainScreen(
            appUiState = appUiState,
            finishPage = navController::popBackStack,
            toSheetDetail = navController::navigateToSheetDetail,
            toFriend = {},
            toMessage = {},
            toScan = {},
            toProfile = {},
            toLogin = navController::navigateToLoginHome,
            toCode = {},
            toSetting = {},
            toAbout = {},
        )
        sheetDetailScreen(
            finishPage = navController::popBackStack,
            toMusicPlayer = navController::navigateToMusicPlayer
        )
        musicPlayerScreen(
            finishPage = navController::popBackStack,
        )
        loginHomeScreen(
            finishPage = navController::popBackStack,
            toLogin = navController::navigateToLogin,
            toCodeLogin = {},
            finishAllLoginPages = navController::finishAllLoginPages,
            toWebPage = {

            },
        )
        loginScreen(
            finishPage = navController::popBackStack,
            toRegister = {},
            toSetPassword = {},
            finishAllLoginPages = navController::finishAllLoginPages,
        )
    }

}