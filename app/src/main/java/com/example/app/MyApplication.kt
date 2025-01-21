package com.example.app

import android.app.Application
import android.util.Log
import com.example.app.core.datastore.SessionPreferences
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication:Application() {

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

    companion object{
        private const val TAG = "MyApplication"
        lateinit var instance:MyApplication
    }
}