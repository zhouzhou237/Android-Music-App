package com.example.app.feature.discovery

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.core.model.Song
import com.example.app.core.network.datasource.MyRetrofitDatasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * 发现界面VM
 */
class DiscoveryViewModel: ViewModel() {
    private val _datum = MutableStateFlow<List<Song>>(emptyList())
    val datum:StateFlow<List<Song>> = _datum

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
         *  test network reauest
         */
        viewModelScope.launch {
            val songs = MyRetrofitDatasource.songs()
            _datum.value = songs.data?.list?: emptyList()
        }


    }

    companion object {
        const val TAG = "DiscoveryViewModel"
    }
}