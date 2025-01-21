package com.example.app

import android.app.Application
import android.util.Log
import com.example.app.core.data.repository.UserDataRepository
import com.example.app.core.datastore.SessionPreferences
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
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