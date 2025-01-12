package com.example.app.core.model.response



/**
 * 网络响应分页模型
 */

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