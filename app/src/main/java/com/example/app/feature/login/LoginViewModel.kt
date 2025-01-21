package com.example.app.feature.login

import android.text.TextUtils
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    fun onLoginClick(username: String, password: String) {

    }
}