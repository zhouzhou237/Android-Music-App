package com.quick.app.core.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp


@Composable
fun dp2Px(dp: Dp) = with(LocalDensity.current) { dp.toPx() }