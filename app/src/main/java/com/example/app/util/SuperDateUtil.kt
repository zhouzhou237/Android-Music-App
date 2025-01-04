package com.example.app.util

import java.util.Calendar

/**
 * 当前年
 */
class SuperDateUtil {
    companion object {
        fun currentYear(): Int {
            return Calendar.getInstance().get(Calendar.YEAR)
        }

        fun currentDayOfMonth(): Int {
            return Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        }
    }
}