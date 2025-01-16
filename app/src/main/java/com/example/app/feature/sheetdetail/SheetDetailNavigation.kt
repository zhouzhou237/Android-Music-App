package com.example.app.feature.sheetdetail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val SHEET_DETAIL_ROUTE = "sheet_detail"
const val SHEET_ID = "sheet_id"

fun NavController.navigateToSheetDetail(sheetId: String): Unit {
    navigate("${SHEET_DETAIL_ROUTE}/$sheetId")
}

fun NavGraphBuilder.sheetDetailScreen(
    finishPage: () -> Unit,
) {
    composable(
        "${SHEET_DETAIL_ROUTE}/{${SHEET_ID}}",
               arguments = listOf(
                   navArgument(SHEET_ID) { type = NavType.StringType},
               )
    ){
        SheetDetailRoute(
            finishPage = finishPage,
       )
    }
}