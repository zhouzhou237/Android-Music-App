package com.example.app.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.app.core.design.theme.MyAppTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.app.util.SuperDateUtil

@Composable
fun SplashRoute(
    toMain: ()-> Unit,
) {
    SplashScreen(
        year = SuperDateUtil.currentYear(),
        toMain = toMain,
    )
}



@Composable
fun SplashScreen(
    year:Int=2024,
    toMain: ()-> Unit = {},
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.primary)
    ) {


        //region 启动页面banner
        Image(painter = painterResource(id = R.drawable.splash_banner),
            contentDescription = "启动banner",
            modifier = Modifier
                .padding(top = 150.dp)
                .align(Alignment.TopCenter)
                .clickable{
                    toMain()
                }
        )
        //endregion



        //region 版权文件
        Text(text = stringResource(id = R.string.copyright,year),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.align(Alignment.BottomCenter)
                .padding(bottom = 30.dp)
        )
        //endregion

    }


}

@Preview(showBackground = true)
@Composable
fun SplashRoutePreview(): Unit {
    MyAppTheme {
        SplashScreen(
            year = 2025,
        )
    }
}
