package com.example.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewModelScope
import com.example.app.core.data.repository.UserDataRepository
import com.example.app.core.model.UserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


@Composable
fun rememberMyAppUiState(
    userDataRepository: UserDataRepository,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): MyAppUiState {
    return remember{
        MyAppUiState(
            coroutineScope,
            userDataRepository,
        )
    }
}


@Stable
class MyAppUiState (
    coroutineScope : CoroutineScope,
    userDataRepository: UserDataRepository,
){
    val userData = userDataRepository.userData.stateIn(
        scope = coroutineScope,
        initialValue = UserData(),
        started = SharingStarted.WhileSubscribed(5_000),
    )

    val isLogin: StateFlow<Boolean> = userDataRepository.userData.map {
        it.isLogin()
    }.stateIn(
        scope = coroutineScope,
        initialValue = false,
        started = SharingStarted.WhileSubscribed(5_000),
    )
}