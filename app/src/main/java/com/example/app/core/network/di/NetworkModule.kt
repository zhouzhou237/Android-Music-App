package com.example.app.core.network.di

import android.util.Log
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.app.MyAppState
import com.example.app.MyApplication
import com.example.app.core.config.Config
import com.example.app.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun okHttpCallFactory(
        okHttpClient: OkHttpClient,
    ): Call.Factory = okHttpClient

    @Provides
    @Singleton
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

            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(
                    if (Config.DEBUG)
                        HttpLoggingInterceptor.Level.BODY
                    else
                        HttpLoggingInterceptor.Level.NONE
                )
            })

            .addInterceptor(ChuckerInterceptor.Builder(MyApplication.instance).build())

            .addInterceptor {
                var request = it.request()

                if (MyAppState.session.isNotBlank()) {

                    Log.d(TAG, "providesOkHttpClient auth: ${MyAppState.session}")

                    request = request.newBuilder()
                        .header(Constant.HEADER_AUTH, MyAppState.session)
                        .build()
                }

                it.proceed(request)
            }

            .build()
    }

    companion object {
        const val TAG = "NetworkModule"
    }

}