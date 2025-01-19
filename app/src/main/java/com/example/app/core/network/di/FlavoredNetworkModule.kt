package com.example.app.core.network.di

import com.example.app.core.network.datasource.MyNetworkDatasource
import com.example.app.core.network.datasource.MyRetrofitDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface FlavoredNetworkModule {
    @Binds
    fun binds(impl: MyRetrofitDatasource): MyNetworkDatasource

}