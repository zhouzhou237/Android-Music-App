package com.quick.app.feature.mediaplayer

import androidx.lifecycle.ViewModel
import com.example.app.core.data.repository.SongRepository
import com.example.app.core.data.repository.UserDataRepository
import com.example.app.core.media.MediaServiceConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MusicPlayViewModel @Inject constructor(
    mediaServiceConnection: MediaServiceConnection,
    songRepository: SongRepository,
) : BaseMediaPlayerViewModel(
    mediaServiceConnection,
    songRepository,
) {

}