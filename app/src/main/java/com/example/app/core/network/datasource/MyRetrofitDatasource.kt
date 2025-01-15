package com.example.app.core.network.datasource

import com.example.app.core.config.Config
import com.example.app.core.model.Song
import com.example.app.core.model.response.NetworkPageData
import com.example.app.core.model.response.NetworkResponse
import com.example.app.core.network.di.NetworkModule
import com.example.app.core.network.retrofit.MyNetworkApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.Query

object MyRetrofitDatasource{
    /**
     * network request
     */
    private val service = Retrofit.Builder()
        .baseUrl(Config.ENDPOINT)
        .callFactory(NetworkModule.providesOkHttpClient())
        .addConverterFactory(NetworkModule.providesNetworkJson().asConverterFactory("application/json".toMediaType()))
        .build()
        .create(MyNetworkApiService::class.java)

    suspend fun songs(): NetworkResponse<NetworkPageData<Song>> {
        return service.songs()
    }

    suspend fun songDetail(
        @Query(value = "id") id: String,
    ): NetworkResponse<Song> {
        return service.songDetail(id)
    }
}