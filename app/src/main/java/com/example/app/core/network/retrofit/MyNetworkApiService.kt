package com.example.app.core.network.retrofit

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
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MyNetworkApiService {

    @GET("v1/songs/page")
    suspend fun songs():NetworkResponse<NetworkPageData<Song>>
    @GET("v1/songs/info")
    suspend fun songDetail(
        @Query(value = "id") id: String,
    ): NetworkResponse<Song>

    @GET("v1/indexes")
    suspend fun indexes(@Query(value = "app") app: Int): NetworkResponse<NetworkPageData<ViewData>>
    /**
     * 登录
     */
    @POST("v1/login")
    suspend fun login(
        @Body data: User,
    ): NetworkResponse<Session>

    @POST("v2/wechat-login")
    suspend fun loginWechat(
        @Body data: WechatLoginRequest
    ): NetworkResponse<Session>

    @GET("v1/sheets/info")
    suspend fun sheetDetail( @Query(value = "id") id: String): NetworkResponse<Sheet>

    @POST("v1/users/reset_password")
    suspend fun setPassword(
        @Body data: User
    ): NetworkResponse<BaseId>

    @POST("v1/users/add")
    suspend fun register(
        @Body data: User,
    ): NetworkResponse<BaseId>

    @GET("v1/users/info")
    suspend fun userDetail(@Query(value = "id") id: String): NetworkResponse<User>

    @POST("v1/users/update")
    suspend fun updateUser(
        @Body data: User
    ): NetworkResponse<BaseModel>
}