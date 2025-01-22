package com.example.app.feature.sheetdetail


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.core.data.repository.SheetRepository
import com.example.app.core.data.repository.SongRepository
import com.example.app.core.data.repository.UserDataRepository
import com.example.app.core.data.repository.UserRepository
import com.example.app.core.exception.localException
import com.example.app.core.media.MediaServiceConnection
import com.example.app.core.model.Sheet
import com.example.app.core.result.asResult
import com.quick.app.feature.mediaplayer.BaseMediaPlayerViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * 歌单详情VM
 */
@HiltViewModel
class SheetDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val sheetRepository: SheetRepository,
    mediaServiceConnection: MediaServiceConnection,
    songRepository: SongRepository,
    private val userRepository: UserRepository,
) : BaseMediaPlayerViewModel(
    mediaServiceConnection,
    songRepository,
) {
    val sheetId: String = checkNotNull(savedStateHandle[SHEET_ID])

    private val _uiState = MutableStateFlow<SheetDetailUiState>(SheetDetailUiState.Loading)
    val uiState: StateFlow<SheetDetailUiState> = _uiState

    private lateinit var data: Sheet

    init{
        loadData()
    }


    private fun loadData() {
        viewModelScope.launch {
            sheetRepository.sheetDetail(sheetId)
                .asResult()
                .collectLatest { r ->
                    if (r.isSuccess) {
                        data = r.getOrThrow().data!!
                        _uiState.value = SheetDetailUiState.Success(data)
                    }else {
                        _uiState.value =
                            SheetDetailUiState.Error(r.exceptionOrNull()!!.localException())
                    }

                }
        }
    }

    fun onRetryClick() {
        loadData()
    }

    fun onSongClick(index: Int) {
        setMediasAndPlay(data.songs!!, index, true)
    }

    companion object{
        const val TAG = "SheetDetailViewModel"
    }
}