package com.example.app

import android.app.Application
import android.util.Log
import com.example.app.core.data.repository.UserDataRepository
import com.example.app.core.datastore.SessionPreferences
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltAndroidApp
class MyApplication:Application() {

    @Inject
    lateinit var userDataRepository: UserDataRepository
    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(){
        super.onCreate()
        instance = this
        Log.d(TAG,"MyApplication onCreate")

        // Listen to user information and update it to the cache object.
        applicationScope.launch {
            userDataRepository.userData
                .map { it.session }
                .distinctUntilChanged()
                .collectLatest {
                    MyAppState.session = it.session
                    MyAppState.userId = it.userId
                }
        }
    }


    /**
     * 登录后初始化
     */
    private var isInitAfterLogin = false
    fun initAfterLogin(session: SessionPreferences) {
//        destroyInstance()
        if (isInitAfterLogin) {
            return
        }

        isInitAfterLogin = true
    }


    fun logout() {
        logoutSilence()
    }

    private fun logoutSilence() {
        isInitAfterLogin = false

        applicationScope.launch {
            //清除登录相关信息
            userDataRepository.logout()
        }

//        destroyInstance()
    }


    companion object{
        private const val TAG = "MyApplication"
        lateinit var instance:MyApplication
    }
}