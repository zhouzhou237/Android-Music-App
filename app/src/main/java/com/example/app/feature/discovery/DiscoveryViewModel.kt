package com.example.app.feature.discovery

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.core.data.repository.CommonRepository
import com.example.app.core.model.Song
import com.example.app.core.model.ViewData
import com.example.app.core.network.datasource.MyRetrofitDatasource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 发现界面VM
 */
@HiltViewModel
class DiscoveryViewModel @Inject constructor(
    private val commonRepository: CommonRepository
): ViewModel() {
    private val _topDatum = MutableStateFlow<List<ViewData>>(emptyList())
    val topDatum:StateFlow<List<ViewData>> =_topDatum

    init {
        loadData()
    }

    private fun loadData() {
        //_datum.value = DiscoveryPreviewParameterData.SONGS

        //val json = Json.encodeToString(DiscoveryPreviewParameterData.SONG)
        //Log.d(TAG, "encodeToString: $json")

        //val obj = Json.decodeFromString<Song>(json)
        //Log.d(TAG, "decodeFromString: $obj")


        /**
         *  test network request
         */
        viewModelScope.launch {
               val indexes = commonRepository.indexes(app = 30)
               _topDatum.value = indexes.data?.list?: emptyList()
        }
    }

    companion object {
        const val TAG = "DiscoveryViewModel"
    }
}