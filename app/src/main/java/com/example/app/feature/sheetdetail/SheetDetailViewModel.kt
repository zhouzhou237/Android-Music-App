package com.example.app.feature.sheetdetail


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.core.data.repository.SheetRepository
import com.example.app.core.model.SHEET_EMPTY
import com.example.app.core.model.Sheet
import com.example.app.core.result.asResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * 歌单详情VM
 */
class SheetDetailViewModel(
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    val sheetId: String = checkNotNull(savedStateHandle[SHEET_ID])

    private val _data = MutableStateFlow<SheetDetailUiState>(SheetDetailUiState.Loading)
    val data: StateFlow<SheetDetailUiState> = _data
    private val sheetRepository = SheetRepository()

    init{
        loadData()
    }


    private fun loadData() {
        viewModelScope.launch {
            sheetRepository.sheetDetail(sheetId)
                .asResult()
                .collectLatest { r ->
                    if (r.isSuccess) {
                        _data.value = SheetDetailUiState.Success(r.getOrThrow().data !!)
                    }else {
                        _data.value = SheetDetailUiState.Error(r.exceptionOrNull()!!.localizedMessage)
                    }

                }
        }
    }

    fun onRetryClick() {
        loadData()
    }

    companion object{
        const val TAG = "SheetDetailViewModel"
    }
}