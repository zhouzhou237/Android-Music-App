package com.example.app.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.R
import com.example.app.ui.theme.AppTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

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

        //版权文件
        Text(text = stringResource(id = R.string.copyright),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.align(Alignment.BottomCenter)
                .padding(bottom = 30.dp)
        )

    }


}

@Preview(showBackground = true)
@Composable
fun SplashRoutePreview(): Unit {
    AppTheme {
        SplashRoute()
    }
}
