package com.example.app.feature.web

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder




@Serializable
data class WebParam(
    val uri: String? = null,
    val content: String? = null,
)