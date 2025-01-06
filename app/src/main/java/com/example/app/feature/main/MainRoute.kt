package com.example.app.feature.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainRoute(
    finishPage: () -> Unit,
) {
    MainScreen(
        finishPage = finishPage
    )
}

@Composable
fun MainScreen(
    finishPage: () -> Unit = {}
) {
    Button(onClick = finishPage, modifier = Modifier.padding(top = 100.dp)) {
        Text("Close")
    }
}