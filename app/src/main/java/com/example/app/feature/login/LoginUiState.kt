package com.example.app.feature.login

import com.example.app.core.exception.CommonException



/**
 * login page state
 */
sealed interface LoginUiState {
    /**
     * None - Initial state
     */
    data object None : LoginUiState
    /**
     * Success
     */
    data object Success: LoginUiState

    /**
     * Loading
     */
    data object Loading : LoginUiState

    data class Error(
        val exception: CommonException,
    ) : LoginUiState

    data class ErrorRes(
        val data: Int,
    ) : LoginUiState
}