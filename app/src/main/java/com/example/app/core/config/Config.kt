package com.example.app.core.config

import com.example.app.BuildConfig


/**
 * 配置文件
 */
object Config {

    /**
     * 是否是调试模式
     */
    val DEBUG: Boolean = BuildConfig.DEBUG

    /**
     * 端点
     */
    const val ENDPOINT = "https://cloud-music-isep-1b9bd091d8ad.herokuapp.com/"


    /**
     * 资源端点
     */

    var RESOURCE_ENDPOINT = "https://com-quick-project.oss-cn-beijing.aliyuncs.com/%s"


    var RESOURCE_ENDPOINT2 = "http://course-music-dev.ixuea.com/%s"

}