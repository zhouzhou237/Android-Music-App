package com.example.app.feature.login

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.MyApplication
import com.example.app.R
import com.example.app.core.model.User
import com.example.app.util.StringUtil
import com.example.app.util.SuperRegularUtil
import com.example.app.core.data.repository.SessionRepository
import com.example.app.core.data.repository.UserDataRepository
import com.example.app.core.exception.localException
import com.example.app.core.result.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val userDataRepository: UserDataRepository,
): ViewModel() {


    val uiState = MutableStateFlow<LoginUiState>(LoginUiState.None)

    fun onLoginClick(username: String, password: String) {

        // parameter check
        if (username.isBlank()) {
            uiState.value = LoginUiState.ErrorRes(R.string.enter_phone_or_email)
            return
        }
        //R.string.entre_phone_or_email
        // Check if the username is neither a phone number nor an email address
        // If it's neither, the format is incorrect

        if (!(SuperRegularUtil.isPhone(username) || SuperRegularUtil.isEmail(username))) {
            setUiStateErrorRes(R.string.error_username_format)
            return
        }

        if (TextUtils.isEmpty(password)) {
            // Set error state for empty password
            setUiStateErrorRes(R.string.enter_password)
            return
        }

        // Validate password format
        if (!StringUtil.isPassword(password)) {
            setUiStateErrorRes(R.string.error_password_format)
            return
        }

        val param = User(
            phone = if (SuperRegularUtil.isPhone(username)) username else "",
            email = if (SuperRegularUtil.isEmail(username)) username else "",
            password = password
        )
        login(param)
    }

    private fun setUiStateErrorRes(res: Int) {
        // Update UI state with the provided error resource ID
        uiState.value = LoginUiState.ErrorRes(res)
    }

    private  fun login(param: User){
        viewModelScope.launch {
            sessionRepository.login(param)
                .asResult()
                .collectLatest {
                    if (it.isSuccess){
                        Log.d("TAG", "login: ${it.getOrThrow().data}")
                        val result = it.getOrThrow()

                        // Save login information
                        val sessionPreferences = result.data!!.toPreferences()
                        userDataRepository.setSession(sessionPreferences)
                        userDataRepository.setUser(result.data.user.toPreferences())

                        MyApplication.instance.initAfterLogin(sessionPreferences!!)

                        // Initialize the app after login
                        // MyApplication.instance.initAfterLogin(sessionPreferences!!)
                        uiState.value = LoginUiState.Success
                    } else {
                        uiState.value = LoginUiState.Error(it.exceptionOrNull()!!.localException())
                    }
                }
        }
    }
    fun resetUiState(){
        uiState.value = LoginUiState.None
    }
}
