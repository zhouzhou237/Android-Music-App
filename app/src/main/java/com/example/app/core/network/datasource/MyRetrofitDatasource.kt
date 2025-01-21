package com.example.app.core.network.datasource

import okhttp3.Call
import com.example.app.core.config.Config
import com.example.app.core.model.Session
import com.example.app.core.model.Sheet
import com.example.app.core.model.Song
import com.example.app.core.model.User
import com.example.app.core.model.ViewData
import com.example.app.core.model.WechatLoginRequest
import com.example.app.core.model.response.NetworkPageData
import com.example.app.core.model.response.NetworkResponse
import com.example.app.core.network.retrofit.MyNetworkApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.Query
import javax.inject.Inject

class MyRetrofitDatasource @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
) : MyNetworkDatasource {
    /**
     * network request
     */
    private val service = Retrofit.Builder()
        .baseUrl(Config.ENDPOINT)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(MyNetworkApiService::class.java)

    override suspend fun songs(): NetworkResponse<NetworkPageData<Song>> {
        return service.songs()
    }

    override suspend fun songDetail(
        @Query(value = "id") id: String,
    ): NetworkResponse<Song> {
        return service.songDetail(id)
    }

    override suspend fun indexes(app: Int): NetworkResponse<NetworkPageData<ViewData>>{
        return service.indexes(app)
    }

    override suspend fun sheetDetail(id: String): NetworkResponse<Sheet>{
        return service.sheetDetail(id)
    }

    override suspend fun login(data: User): NetworkResponse<Session> {
        return service.login(data)
    }

    override suspend fun loginWechat(data: WechatLoginRequest): NetworkResponse<Session> {
        return service.loginWechat(data)
    }

}