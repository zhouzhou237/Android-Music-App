package com.example.app.core.network.datasource

import com.example.app.core.config.Config
import com.example.app.core.model.Sheet
import com.example.app.core.model.Song
import com.example.app.core.model.ViewData
import com.example.app.core.model.response.NetworkPageData
import com.example.app.core.model.response.NetworkResponse
import com.example.app.core.network.di.NetworkModule
import com.example.app.core.network.retrofit.MyNetworkApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.Query

interface MyNetworkDatasource {

    suspend fun songs(): NetworkResponse<NetworkPageData<Song>>

    suspend fun songDetail(
        @Query(value = "id") id: String,
    ): NetworkResponse<Song>

    suspend fun indexes(app: Int): NetworkResponse<NetworkPageData<ViewData>>

    suspend fun sheetDetail(id: String): NetworkResponse<Sheet>
}