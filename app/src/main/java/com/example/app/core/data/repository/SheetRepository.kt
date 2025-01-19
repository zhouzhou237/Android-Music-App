package com.example.app.core.data.repository

import com.example.app.core.model.Sheet
import com.example.app.core.model.response.NetworkResponse
import com.example.app.core.network.datasource.MyNetworkDatasource
import com.example.app.core.network.datasource.MyRetrofitDatasource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SheetRepository @Inject constructor(
       private val networkDatasource: MyNetworkDatasource,
) {

    fun sheetDetail(
        id: String,
    ): Flow<NetworkResponse<Sheet>> = flow {
        emit(
            networkDatasource.sheetDetail(id)
        )
    }.flowOn(Dispatchers.IO)
}