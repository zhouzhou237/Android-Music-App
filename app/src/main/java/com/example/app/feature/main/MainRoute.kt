package com.example.app.feature.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.app.core.design.component.MyNavigationBar
import com.example.app.core.design.theme.SpaceExtraSmallHeight
import com.example.app.feature.discovery.DiscoveryRoute
import com.example.app.feature.feed.FeedRoute
import com.example.app.feature.me.MeRoute
import com.example.app.feature.shortvideo.ShortVideoRoute
import kotlinx.coroutines.launch

@Composable
fun MainRoute(
    finishPage: () -> Unit,
    toSheetDetail: (String) -> Unit,
) {
    MainScreen(
        toSheetDetail = toSheetDetail,
    )
}

@Composable
fun MainScreen(
    finishPage: () -> Unit = {},
    toSheetDetail: (String) -> Unit = {},
) {
    //Name of the currently selected interface
    var currentDestination by rememberSaveable {
        mutableStateOf(TopLevelDestination.DISCOVERY.route)
    }

    val pagerState = rememberPagerState {
        4
    }

    val scope = rememberCoroutineScope()

    var isPausePlay by rememberSaveable {
        mutableStateOf(true)
    }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            beyondViewportPageCount = 4, //Load more number of off-screen pages
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            when (page) {
                0 -> DiscoveryRoute(
                    toSheetDetail = toSheetDetail
                )
                1 -> ShortVideoRoute()
                2 -> MeRoute()
                3 -> FeedRoute()
            }
        }

        SpaceExtraSmallHeight()
        MyNavigationBar(
            destinations = TopLevelDestination.entries,
            currentDestination = currentDestination,
            onNavigateToDestination = { index ->
                currentDestination= TopLevelDestination.values()[index].route
                scope.launch {
                    pagerState.scrollToPage(index)
                }

            },
            modifier = Modifier
        )
    }
}