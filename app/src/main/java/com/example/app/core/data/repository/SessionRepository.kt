package com.example.app.core.data.repository

import com.example.app.core.model.Session
import com.example.app.core.model.User
import com.example.app.core.model.WechatLoginRequest
import com.example.app.core.model.response.NetworkResponse
import com.example.app.core.network.datasource.MyNetworkDatasource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.http.Body
import javax.inject.Inject

/**
 * 用户会话仓库
 */
class SessionRepository @Inject constructor(
    private val networkDataSource: MyNetworkDatasource,
) {
    suspend fun login(
        @Body data: User,
    ):Flow<NetworkResponse<Session>> = flow {
        emit(
            networkDataSource.login(data)
        )
    }.flowOn(Dispatchers.IO)

    fun loginWechat(data: WechatLoginRequest): Flow<NetworkResponse<Session>> = flow {
        emit(
            networkDataSource.loginWechat(data)
        )
    }.flowOn(Dispatchers.IO)
}