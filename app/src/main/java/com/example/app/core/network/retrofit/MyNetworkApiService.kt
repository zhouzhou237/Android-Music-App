package com.example.app.core.network.retrofit

import com.example.app.core.model.Song
import com.example.app.core.model.response.NetworkPageData
import com.example.app.core.model.response.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MyNetworkApiService {

    @GET("v1/songs/page")
    suspend fun songs():NetworkResponse<NetworkPageData<Song>>
    @GET("v1/songs/info")
    suspend fun songDetail(
        @Query(value = "id") id: String,
    ): NetworkResponse<Song>



}