package com.example.app.core.network.datasource

import com.example.app.core.config.Config
import com.example.app.core.model.BaseId
import com.example.app.core.model.BaseModel
import com.example.app.core.model.Session
import com.example.app.core.model.Sheet
import com.example.app.core.model.Song
import com.example.app.core.model.User
import com.example.app.core.model.ViewData
import com.example.app.core.model.WechatLoginRequest
import com.example.app.core.model.response.NetworkPageData
import com.example.app.core.model.response.NetworkResponse
import com.example.app.core.network.di.NetworkModule
import com.example.app.core.network.retrofit.MyNetworkApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.Query

interface MyNetworkDatasource {

    suspend fun songs(): NetworkResponse<NetworkPageData<Song>>

    suspend fun songDetail(
        @Query(value = "id") id: String,
    ): NetworkResponse<Song>

    suspend fun indexes(app: Int): NetworkResponse<NetworkPageData<ViewData>>

    suspend fun sheetDetail(id: String): NetworkResponse<Sheet>
    /**
     * 登录
     */
    suspend fun login(
        data: User,
    ): NetworkResponse<Session>
    /**
     * 微信登录
     *
     * 通过code
     */
    suspend fun loginWechat(
        data: WechatLoginRequest
    ): NetworkResponse<Session>

    suspend fun register(
        @Body data: User,
    ): NetworkResponse<BaseId>

    suspend fun setPassword(
        @Body data: User
    ): NetworkResponse<BaseId>

    suspend fun userDetail(id: String): NetworkResponse<User>

    suspend fun updateUser(
        @Body data: User
    ): NetworkResponse<BaseModel>
}