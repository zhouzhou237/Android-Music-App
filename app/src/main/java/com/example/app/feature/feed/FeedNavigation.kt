package com.example.app.feature.feed

import androidx.navigation.NavController

const val FEED_ROUTE = "feeds"

fun NavController.navigateToFeed() {
    navigate(FEED_ROUTE) {
        launchSingleTop = true
    }
}