package com.example.app.core.network.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.app.MyApplication
import com.example.app.core.config.Config
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object NetworkModule {

    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    fun okHttpCallFactory(
        okHttpClient: OkHttpClient,
    ): Call.Factory = okHttpClient

    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS) //连接超时时间
            .writeTimeout(10, TimeUnit.SECONDS) //写超时时间
            .readTimeout(10, TimeUnit.SECONDS) //读超时时间
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(
                    if (Config.DEBUG)
                        HttpLoggingInterceptor.Level.BODY
                    else
                        HttpLoggingInterceptor.Level.NONE
                )
            })

            //添加chucker实现应用内显示网络请求信息拦截器
            .addInterceptor(ChuckerInterceptor.Builder(MyApplication.instance).build())

            .build()
    }

}