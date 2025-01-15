package com.example.app.feature.discovery

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.core.model.Song
import com.example.app.core.model.ViewData
import com.example.app.core.network.datasource.MyRetrofitDatasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * 发现界面VM
 */
class DiscoveryViewModel: ViewModel() {
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
            val indexes = MyRetrofitDatasource.indexes(app = 30)
            _topDatum.value = indexes.data?.list ?: emptyList()
        }
    }

    companion object {
        const val TAG = "DiscoveryViewModel"
    }
}