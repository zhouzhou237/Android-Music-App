package com.example.app.core.data.repository

import com.example.app.core.network.datasource.MyNetworkDatasource
import com.example.app.util.Constant
import javax.inject.Inject

class CommonRepository @Inject constructor(
    private val networkDatasource: MyNetworkDatasource,
){
    suspend fun indexes(
        app: Int = Constant.VALUE30
    ) = networkDatasource.indexes(
        app = app,
    )
}