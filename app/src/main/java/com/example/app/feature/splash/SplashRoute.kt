package com.example.app.feature.splash

import android.window.SplashScreen
import androidx.annotation.IntRange
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.R
import com.example.app.ui.theme.AppTheme

@Composable
fun SplashRoute() {
    SplashScreen()
}

@Composable
fun SplashScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.primary)) {


        //region 启动页面banner
        Image(painter = painterResource(id = R.drawable.splash_banner),
            contentDescription = "启动logo",
            modifier = Modifier.padding(top = 70.dp)
                .align(Alignment.TopCenter)
        )
        //endregion

        //region 启动页面banner
        Image(painter = painterResource(id = R.drawable.splash_logo),
            contentDescription = "启动logo",
            modifier = Modifier.padding(top = 70.dp)
                .align(Alignment.BottomCenter)
        )
        //endregion
    }
}

@Preview(showBackground = true)
@Composable
fun SplashRoutePreview(): Unit {
    AppTheme {
        SplashRoute()
    }
}
