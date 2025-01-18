package com.example.app.core.exception

import com.example.app.core.model.response.NetworkResponse

class CommonException (
    /**
     * 网络请求
     */
    val networkResponse : NetworkResponse<*>? = null,

    val throwable : Throwable? = null,
    var tipString: String? = null,
    var tipIcon: Int? = null,
) : RuntimeException()


