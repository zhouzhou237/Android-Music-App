package com.example.app.feature.sheetdetail


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.core.model.SHEET_EMPTY
import com.example.app.core.model.Sheet
import com.example.app.core.network.datasource.MyRetrofitDatasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


/**
 * 歌单详情VM
 */
class SheetDetailViewModel(
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    val sheetId: String = checkNotNull(savedStateHandle[SHEET_ID])

    private val _data = MutableStateFlow<Sheet>(SHEET_EMPTY())
    val data: StateFlow<Sheet> = _data

    init{
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val r = MyRetrofitDatasource.sheetDetail(sheetId)
            _data.value = r.data ?: SHEET_EMPTY()
        }
    }

    companion object{
        const val TAG = "SheetDetailViewModel"
    }
}