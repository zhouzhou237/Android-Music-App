package com.example.app.util

import com.example.app.core.config.Config

object ResourceUtil {
    /**
     * 将相对资源转为绝对路径
     *
     * @param uri 放到自己服务端的资源以r开头，其他资源都在阿里云oss
     * @return
     */
    fun r(uri: String): String {
        return if (uri.startsWith("http")) {
            uri
        } else if (uri.startsWith("files"))
            uri
        else if (uri.startsWith("r/")) {
            //上传到服务端的资源
            "${Config.ENDPOINT}${uri}"
        }
        else
            String.format(Config.RESOURCE_ENDPOINT, uri)
    }

    fun r2(uri: String): String {
        if (uri.startsWith("http")) {
            return uri
        }
        return if (uri.startsWith("files"))
            uri
        else
            String.format(Config.RESOURCE_ENDPOINT2, uri)
    }
}