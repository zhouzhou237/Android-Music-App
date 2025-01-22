package com.example.app.feature.loginhome

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app.MainActivity
import com.example.app.MyApplication
import com.example.app.R
import com.example.app.core.config.Config
import com.example.app.core.config.Config.LINK_USER_USER_AGREEMENT
import com.example.app.core.design.theme.MyAppTheme
import com.example.app.core.design.theme.Space3XLarge
import com.example.app.core.design.theme.Space4XLarge
import com.example.app.core.design.theme.SpaceLarge
import com.example.app.core.design.theme.md_theme_second_button_link
import com.example.app.feature.web.WebParam

@Composable
fun LoginHomeRoute(
    finishPage: () -> Unit,
    toLogin: () -> Unit,
    toCodeLogin: () -> Unit,
    finishAllLoginPages: () -> Unit,
    toWebPage: (WebParam) -> Unit,
    viewModel: LoginHomeViewModule = hiltViewModel(),
)  {

    LoginHomeScreen(
        finishPage = finishPage,
        toLogin = toLogin,
        toCodeLogin = toCodeLogin,
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginHomeScreen(
    finishPage: () -> Unit = {},
    toLogin: () -> Unit = {},
    toCodeLogin: () -> Unit = {},
    toWebPage: (WebParam) -> Unit = {},
) {
    Scaffold(

        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = finishPage) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = null
                        )
                    }
                },
                title = {

                },
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(paddingValues)
        ){
            Image(
                painter = painterResource(id = R.drawable.login_logo),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 60.dp)
                    .size(100.dp)
                    .clip(MaterialTheme.shapes.extraSmall)
            )

            //底部容器
            BottomView(
                toLogin = toLogin,
                toCodeLogin = toCodeLogin,
                toWebPage = toWebPage,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(
                        start = SpaceLarge, end = SpaceLarge,
                        bottom = Space3XLarge
                    )
            )
        }
    }
}

@Composable
fun BottomView(
    toLogin: () -> Unit = {},
    toCodeLogin: () -> Unit = {},
    finishAllLoginPages: () -> Unit = {},
    toWebPage: (WebParam) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Button(
            onClick = toCodeLogin,
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.code_login))
        }

        OutlinedButton(
            onClick = toLogin,
            modifier = Modifier
                .padding(top = Space4XLarge)
                .height(48.dp)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.username_login))
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(top = 70.dp)
                .fillMaxWidth(),
        ) {

            OtherLoginButton(
                icon = R.drawable.passport_sns_wechat,
                onClick = {

                }
            )

            OtherLoginButton(
                icon = R.drawable.passport_sns_facebook,
                onClick = {

                }
            )

            OtherLoginButton(
                icon = R.drawable.passport_sns_google
            )

            OtherLoginButton(
                icon = R.drawable.passport_sns_ins
            )
        }

        UserAgreement(
            toWebPage = toWebPage,
        )
    }
}

@Composable
fun UserAgreement(
    toWebPage: (WebParam) -> Unit
) {
    val text = buildAnnotatedString {
        append(stringResource(id = R.string.user_agreement_start))
        withStyle(
            style = SpanStyle(
                color = md_theme_second_button_link,
            )
        ) {
            pushStringAnnotation(
                USER_AGREEMENT_TAG,
                LINK_USER_USER_AGREEMENT
            )
            append(stringResource(id = R.string.user_agreement))
        }
        append(stringResource(id = R.string.user_agreement_and))
        withStyle(
            style = SpanStyle(
                color = md_theme_second_button_link
            )
        ) {
            pushStringAnnotation(
                PRIVACY_POLICY_TAG,
                annotation = Config.LINK_USER_PRIVACY_POLICY
            )
            append(stringResource(id = R.string.user_agreement_privacy_policy))
        }
    }

    ClickableText(
        text = text,
        style = MaterialTheme.typography.labelSmall.copy(
            color = MaterialTheme.colorScheme.outline,
        ),
        onClick = {
            //第一个点击事件
            text.getStringAnnotations(tag = USER_AGREEMENT_TAG, start = it, end = it)
                .firstOrNull()?.let {
                    toWebPage(WebParam(uri = it.item))
                }

            //第二个点击事件
            text.getStringAnnotations(tag = PRIVACY_POLICY_TAG, start = it, end = it)
                .firstOrNull()?.let {
                    toWebPage(WebParam(uri = it.item))
                }
        },
        modifier = Modifier.padding(top = Space3XLarge)
    )
}

const val USER_AGREEMENT_TAG = "USER_AGREEMENT_TAG"
const val PRIVACY_POLICY_TAG = "PRIVACY_POLICY_TAG"


@Composable
fun OtherLoginButton(
    @DrawableRes icon: Int,
    onClick: () -> Unit = {},
) {
    Image(
        painter = painterResource(id = icon),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .clickable {
                onClick()
            }
    )
}


@Preview(showBackground = true)
@Composable
fun LoginHomeRoutePreview(): Unit {
    MyAppTheme {
        LoginHomeScreen(
        )
    }
}