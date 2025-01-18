package com.example.app.feature.sheetdetail

import android.os.Message
import com.example.app.core.exception.CommonException
import com.example.app.core.model.Sheet


/**
 * 歌单详情界面状态
 */
sealed interface SheetDetailUiState {
    /**
     * 成功
     */
    data class Success(val data: Sheet) : SheetDetailUiState

    /**
     * 加载中
     */
    data object Loading : SheetDetailUiState

    data class Error(
        val message: String,
    ) : SheetDetailUiState
}