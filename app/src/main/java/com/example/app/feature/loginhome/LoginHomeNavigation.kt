package com.example.app.feature.loginhome

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.app.feature.web.WebParam

const val LOGIN_HOME_ROUTE = "login_home"
fun NavController.navigateToLoginHome() = navigate(LOGIN_HOME_ROUTE)

fun NavGraphBuilder.loginHomeScreen(
    finishPage: () -> Unit,
    toLogin: () -> Unit,
    toCodeLogin: () -> Unit,
    finishAllLoginPages: () -> Unit,
    toWebPage: (WebParam) -> Unit,
){
    composable(LOGIN_HOME_ROUTE) {
        LoginHomeRoute(
            finishPage = finishPage,
            toLogin = toLogin,
            toCodeLogin = toCodeLogin,
            finishAllLoginPages = finishAllLoginPages,
            toWebPage = toWebPage,
        )
    }
}
