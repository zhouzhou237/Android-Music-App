package com.example.app.util

import java.util.regex.Pattern

/**
 * Regular expression
 */

object SuperRegularUtil {
    /**
     * if it's phone number
     *
     * @param data
     * @return
     */
    fun isPhone(data: String): Boolean {
        return Pattern.compile(REGEX_PHONE).matcher(data).matches()
    }

    /**
     * if it's email
     *
     * @param data
     * @return
     */
    fun isEmail(data: String): Boolean {
        return Pattern.compile(REGEX_EMAIL).matcher(data).matches()
    }

    /**
     * 手机号正则表达式
     * 移动：134 135 136 137 138 139 147 150 151 152 157 158 159 178 182 183 184 187 188 198
     * 联通：130 131 132 145 155 156 166 171 175 176 185 186
     * 电信：133 149 153 173 177 180 181 189 199
     * 虚拟运营商: 170
     */
    const val REGEX_PHONE = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$"

    /**
     * Regular expression for validating French mobile phone numbers.
     * Supported formats:
     * - +33 6XXXXXXXX
     * - +33 7XXXXXXXX
     * - 06XXXXXXXX
     * - 07XXXXXXXX
     */
    const val REGEX_FR_PHONE = "^(\\+33|0)(6|7)\\d{8}$"


    /**
     * Regular expression for validating email.
     */
    const val REGEX_EMAIL = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$"
}