package com.example.app.feature.main

import android.text.TextUtils
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.app.R
import com.example.app.core.design.component.MyNavigationBar
import com.example.app.core.design.theme.ArrowIcon
import com.example.app.core.design.theme.SpaceExtraOuter
import com.example.app.core.design.theme.SpaceExtraSmall
import com.example.app.core.design.theme.SpaceExtraSmallHeight
import com.example.app.core.design.theme.SpaceMedium
import com.example.app.core.design.theme.SpaceMediumWidth
import com.example.app.core.design.theme.SpaceOuter
import com.example.app.core.design.theme.SpaceSmall
import com.example.app.core.design.theme.SpaceSmallHeight
import com.example.app.core.design.theme.SpacerOuterHeight
import com.example.app.core.design.theme.bodyXLarge
import com.example.app.core.design.theme.extraSmallRoundedCornerShape
import com.example.app.core.extension.clickableNoRipple
import com.example.app.core.model.UserData
import com.example.app.feature.discovery.DiscoveryRoute
import com.example.app.feature.feed.FeedRoute
import com.example.app.feature.me.MeRoute
import com.example.app.feature.shortvideo.ShortVideoRoute
import com.example.app.util.Constant
import com.example.app.util.ResourceUtil
import kotlinx.coroutines.launch

@Composable
fun MainRoute(
    finishPage: () -> Unit,
    toSheetDetail: (String) -> Unit,
    toFriend: (Int) -> Unit,
    toMessage: () -> Unit,
    toScan: () -> Unit,
    toProfile: () -> Unit,
    toCode: () -> Unit,
    toLogin: () -> Unit,
    toSetting: () -> Unit = {},
    toAbout: () -> Unit = {},
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val toggleDrawer: () -> Unit = {
        scope.launch {
            drawerState.apply {
                if (isClosed) open() else close()
            }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                MyDrawerView(
//                    userData = userData,
//                    isLogin = isLogin,
                    userData = UserData(),
                    isLogin = false,
                    toFriend = toFriend,
                    toMessage = toMessage,
                    toCode = toCode,
                    toProfile = toProfile,
                    toLogin = toLogin,
                    toScan = toScan,
                    toSetting = toSetting,
                    toAbout = toAbout,
                    onLogoutClick = {

                    },
                )
            }
        },
    ) {
        MainScreen(
            toSheetDetail = toSheetDetail,
            toggleDrawer = toggleDrawer,
        )
    }


}


@Composable
fun MyDrawerView(
    userData: UserData,
    isLogin: Boolean,
    toFriend: (Int) -> Unit,
    toMessage: () -> Unit,
    toCode: () -> Unit,
    toProfile: () -> Unit,
    toLogin: () -> Unit,
    toScan: () -> Unit,
    toSetting: () -> Unit = {},
    toAbout: () -> Unit = {},
    onLogoutClick: () -> Unit = {},

) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = SpaceOuter)
    ){
        SpacerOuterHeight()

        MyUserInfoView(
            userData = userData,
            isLogin = isLogin,
            toProfile = toProfile,
            toLogin = toLogin,
            toScan = toScan,
        )

        SpacerOuterHeight()

        //Side-slide display of vinyl records
        Column(
            verticalArrangement = Arrangement.spacedBy(SpaceOuter),
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
        ) {
            MyVipHint()

            MyCard1(
                toMessage = toMessage,
                toFriend = toFriend,
                toCode = toCode,
            )

            (1..2).forEach { _ ->
                MyDrawerCard()
            }

            MySettingCard(
                toSetting = toSetting,
            )

            MyAboutCard(
                toAbout = toAbout,
            )


                OutlinedButton(
                    onClick = onLogoutClick,
                    modifier = Modifier
                        .padding(horizontal = SpaceOuter, vertical = 40.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.logout))
                }

        }
    }
}

@Composable
fun MyAboutCard(
    toAbout: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
    ) {
        MySettingItem(
            title = R.string.my_customer_service,
        )
        SpaceExtraSmallHeight()
        MySettingItem(
            title = R.string.share_app,
        )
        SpaceExtraSmallHeight()
        MySettingItem(
            title = R.string.about,
            onClick = toAbout,
        )
    }
}

@Composable
fun MySettingCard(
    toSetting: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
    ) {
        MySettingItem(
            title = R.string.setting,
            onClick = toSetting,
        )
        SpaceExtraSmallHeight()
        MySettingItem(
            title = R.string.member_center,
        )
        SpaceExtraSmallHeight()
        MySettingItem(
            title = R.string.member_center,

            )
    }
}

@Composable
fun MyDrawerCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
    ) {
        (1..4).forEach { it ->
            MySettingItem()
            if (it != 4)
                SpaceExtraSmallHeight()
        }
    }
}


@Composable
fun MyCard1(
    toMessage: () -> Unit,
    toFriend: (Int) -> Unit,
    toCode: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
    ) {
        MySettingItem(
            title = R.string.my_message,
            onClick = toMessage,
        )
        SpaceExtraSmallHeight()
        MySettingItem(
            title = R.string.my_friend,
            onClick = {
                toFriend(Constant.VALUE0)
            },
        )
        SpaceExtraSmallHeight()
        MySettingItem(
            title = R.string.my_fans,
            onClick = {
                toFriend(Constant.VALUE10)
            },
        )
        SpaceExtraSmallHeight()
        MySettingItem(
            title = R.string.my_code,
            onClick = toCode,
        )
    }
}

@Composable
fun MySettingItem(
    title: Int = R.string.my_message,
    value: String = "",
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable {
                onClick()
            }
            .padding(
                start = SpaceOuter,
                end = SpaceMedium,
                top = SpaceExtraOuter,
                bottom = SpaceExtraOuter
            ),
    ) {
        Icon(
            imageVector = Icons.Default.QrCode,
            contentDescription = null,
        )

        SpaceMediumWidth()

        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.bodyXLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.outline,
            textAlign = TextAlign.Right,
            modifier = Modifier.weight(1f),
        )

        ArrowIcon()
    }
}

@Composable
fun MyVipHint() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .background(colorResource(id = R.color.black42))
            .padding(SpaceOuter)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                //display text buy vip
                text = stringResource(id = R.string.buy_vip),
                style = MaterialTheme.typography.bodyXLarge,
                color = colorResource(id = R.color.black183),
                modifier = Modifier.weight(1f)
            )

            Text(
                //display text Member Center
                text = stringResource(id = R.string.member_center),
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xff837774),
                modifier = Modifier
                    .border(1.dp, colorResource(id = R.color.vip_border), CircleShape)
                    .padding(horizontal = SpaceMedium, vertical = SpaceSmall)

            )
        }

        SpaceSmallHeight()

        Text(
            text = stringResource(id = R.string.vip_hint),
            style = MaterialTheme.typography.bodySmall,
            color = colorResource(id = R.color.vip_border),
        )

        Spacer(
            modifier = Modifier
                .padding(vertical = SpaceMedium)
                .height(SpaceExtraSmall)
                .fillMaxWidth()
                .background(colorResource(id = R.color.divider2))
        )

        Text(
            text = stringResource(id = R.string.vip_hint_price),
            style = MaterialTheme.typography.bodySmall,
            color = colorResource(id = R.color.vip_border),
        )
    }
}


/**
 * User Information
 */
@Composable
fun MyUserInfoView(
    userData: UserData,
    isLogin: Boolean,
    toProfile: () -> Unit,
    toScan: () -> Unit,
    toLogin: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        if (userData.isLogin()) {
            //用户信息
//            UserProfile(
//                userData.user,
//                toProfile = toProfile,
//                toScan = toScan,
//            )
        } else {
            DefaultUserProfile(
                toLogin = toLogin,
                toScan = toScan,
            )
        }
    }
}

private val userIconModifier = Modifier
    .size(30.dp)
    .clip(extraSmallRoundedCornerShape)

@Composable
private fun DefaultUserProfile(
    toLogin: () -> Unit,
    toScan: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickableNoRipple { toLogin() },
    ) {
        Image(
            painter = painterResource(id = R.drawable.default_avatar),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = userIconModifier,
        )

        SpaceMediumWidth()

        Text(
            text = stringResource(id = R.string.login_or_register),
            style = MaterialTheme.typography.bodyXLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )

        ArrowIcon()

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = toScan) {
            Icon(
                painter = painterResource(id = R.drawable.scan),
                contentDescription = null,
                modifier = Modifier.size(36.dp),
            )
        }
    }
}



@Composable
private fun UserProfile(
    toProfile: () -> Unit,
    toScan: () -> Unit,
) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickableNoRipple {
//                toProfile()
//            }
//    ) {
//
//        AsyncImage(
//            model = ResourceUtil.r(data.icon),
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//            modifier = userIconModifier,
//            )
//
//
//        SpaceMediumWidth()
//
//        Text(
//            text = data.nickname,
//            style = MaterialTheme.typography.bodyXLarge,
//            color = MaterialTheme.colorScheme.onSurface,
//        )
//
//
//        ArrowIcon()
//
//        Spacer(modifier = Modifier.weight(1f))
//
//        IconButton(onClick = toScan) {
//            Icon(
//                painter = painterResource(id = R.drawable.scan),
//                contentDescription = null,
//                modifier = Modifier.size(36.dp),
//            )
//        }
//    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    finishPage: () -> Unit = {},
    toSheetDetail: (String) -> Unit = {},
    toggleDrawer: () -> Unit = {},

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
                    toSheetDetail = toSheetDetail,
                    toggleDrawer = toggleDrawer,
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