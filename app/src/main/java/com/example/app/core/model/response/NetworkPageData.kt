package com.example.app.core.model.response

import kotlinx.serialization.Serializable


/**
 * 网络响应分页模型
 */
@Serializable
data class NetworkPageData<T>(
    /**
     * 列表
     */
    val list: List<T>? = null,

    /**
     * 分页数据
     */
    val pagination: NetworkPageMeta? = null,
)