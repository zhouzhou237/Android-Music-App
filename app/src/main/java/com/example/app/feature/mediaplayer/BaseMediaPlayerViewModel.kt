package com.quick.app.feature.mediaplayer

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.MimeTypes
import com.example.app.core.media.MediaServiceConnection
import com.example.app.core.model.Song
import com.example.app.core.model.from
import com.example.app.util.ResourceUtil
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

open class BaseMediaPlayerViewModel(
    protected val mediaServiceConnection: MediaServiceConnection,

    ) : ViewModel() {

    val toMusicPlayer = mutableStateOf<Boolean>(false)
    val nowPlaying = mediaServiceConnection.nowPlaying
    val playbackState = mediaServiceConnection.playbackState
    val currentPosition = mediaServiceConnection.currentPosition.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0,
    )

//    val playRepeatMode = userDataRepository.userData.map { it.playRepeatMode }.stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5_000),
//        initialValue = PlaybackMode.REPEAT_LIST,
//    )

    public fun setMediasAndPlay(
        datum: List<Song>,
        index: Int,
        navigateToMusicPlayer: Boolean = false,
    ) {
        viewModelScope.launch {

            //转为MediaItem
            val mediaItems = datum.map {
                MediaItem.Builder()
                    .apply {
                        setMediaId(it.id)
                        setUri(ResourceUtil.r2(it.uri))
                        setMimeType(MimeTypes.AUDIO_MPEG)
                        setMediaMetadata(
                            MediaMetadata.Builder()
                                .from(it)
                                .apply {
                                    setArtworkUri(Uri.parse(ResourceUtil.r2(it.icon))) // Used by ExoPlayer and Notification
                                    // Keep the original artwork URI for being included in Cast metadata object.
//                                        val extras = Bundle()
//                                        extras.putString(ORIGINAL_ARTWORK_URI_KEY, it.image)
//                                        setExtras(extras)
                                }
                                .build()
                        )
                    }.build()
            }.toList()

            mediaServiceConnection.setMediasAndPlay(mediaItems, index)

            toMusicPlayer.value = navigateToMusicPlayer
        }
    }

    fun clearMusicPlayer(): Unit {
        toMusicPlayer.value = false
    }

    fun onSeek(data: Float) {
        mediaServiceConnection.seekTo(data.toLong())
    }

    fun onPreviousClick() {
        mediaServiceConnection.seekToPrevious()
    }

    fun onNextClick() {
        mediaServiceConnection.seekToNext()
    }

    fun onPlayOrPauseClick() {
        mediaServiceConnection.playOrPause()
    }

    fun onChangeRepeatModeClick() {
        viewModelScope.launch {
//            var playRepeatMode = userDataRepository.userData.first().playRepeatMode.ordinal
//            playRepeatMode++
//            if (playRepeatMode > PlaybackMode.REPEAT_SHUFFLE.ordinal) {
//                playRepeatMode = PlaybackMode.REPEAT_LIST.ordinal
//            }
//            userDataRepository.setRepeatModel(
//                PlaybackModePreferences.forNumber(playRepeatMode)
//            )

//            mediaServiceConnection.setRepeatMode(playRepeatMode)
        }
    }
}
