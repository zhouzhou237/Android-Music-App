package com.example.app.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.MyApplication
import com.example.app.core.data.repository.UserDataRepository
import com.example.app.core.model.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val userDataRepository: UserDataRepository,
): ViewModel() {


    fun onLogoutClick() {
        MyApplication.instance.logout()
    }
}