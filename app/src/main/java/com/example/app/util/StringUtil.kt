package com.example.app.util

/**
 * 字符串工具类
 */
object StringUtil {
    /**
     * if it respect the form of password
     *
     * @param value
     * @return
     */
    fun isPassword(value: String): Boolean {
        return value.length in 6..20 //kotlin expression
//        return value.length >=6 && value.length<=20  java expression
    }

    fun isNickname(data: String): Boolean {
        return data.length in 2..10
    }

    fun formatCount(data: Long): String {
        if (data >= 9999) {
            //保留1位小数
            return String.format("%.1f万", data * 1.0 / 10000)
        }
        return data.toString()
    }

}