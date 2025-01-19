package com.example.app.core.media

import android.content.ComponentName
import android.content.Context
import android.media.browse.MediaBrowser
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player

import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.guava.await
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


open class MediaServiceConnection(
    context: Context,
    serviceComponent: ComponentName,
) {

    private var mediaController: MediaController? = null
    private var coroutineContext: CoroutineContext = Dispatchers.Main
    private var scope = CoroutineScope(coroutineContext + SupervisorJob())

    private val playerListener: PlayerListener = PlayerListener()
    private lateinit var currentPlayer: Player

    private inner class PlayerListener : Player.Listener {

        override fun onEvents(player: Player, events: Player.Events) {
            currentPlayer = player
            if (events.contains(Player.EVENT_PLAY_WHEN_READY_CHANGED)
                || events.contains(Player.EVENT_PLAYBACK_STATE_CHANGED)
                || events.contains(Player.EVENT_MEDIA_ITEM_TRANSITION)
            ) {
//                updatePlaybackState(player)
//                if (player.playbackState != Player.STATE_IDLE) {
//                    networkFailure.value = false
//                }
            }
            if (events.contains(Player.EVENT_MEDIA_METADATA_CHANGED)
                || events.contains(Player.EVENT_MEDIA_ITEM_TRANSITION)
                || events.contains(Player.EVENT_PLAY_WHEN_READY_CHANGED)
            ) {

//                updateNowPlaying(player)
            }
        }

        override fun onPlayerErrorChanged(error: PlaybackException?) {
            error?.printStackTrace()
            when (error?.errorCode) {
                PlaybackException.ERROR_CODE_IO_BAD_HTTP_STATUS,
                PlaybackException.ERROR_CODE_IO_INVALID_HTTP_CONTENT_TYPE,
                PlaybackException.ERROR_CODE_IO_CLEARTEXT_NOT_PERMITTED,
                PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED,
                PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT -> {
//                    networkFailure.value = true
                }
            }
        }
    }

    init {
        scope.launch {

            val sessionToken =
                SessionToken(context, serviceComponent)

            mediaController = MediaController
                .Builder(context, sessionToken).setListener(MediaControllerListener())
                .buildAsync().await()

            mediaController!!.addListener(playerListener)

//            initPlayList()
        }
    }


    fun setMediasAndPlay(datum: List<MediaItem>, startIndex: Int) {
        setMedias(datum,startIndex)

        mediaController!!.prepare()
        mediaController!!.play()
    }

    fun setMedias(datum: List<MediaItem>, startIndex: Int, startPositionMs: Long = 0) {
        mediaController!!.setMediaItems(
            datum, startIndex, startPositionMs
        )
    }

    fun playOrPause() {
        mediaController?.run {
            if (isPlaying) {
                pause()
            } else {
                play()
            }
        }
    }


    private inner class MediaControllerListener : MediaController.Listener {
        override fun onDisconnected(controller: MediaController) {
            release()
        }
    }

    fun release() {
//        nowPlaying.value = NOTHING_PLAYING
        mediaController?.let {
            it.removeListener(playerListener)
            it.release()
        }
        instance = null
    }



    companion object{

        private const val TAG = "MediaServiceConnection"

        @Volatile
        private var instance: MediaServiceConnection? = null

        fun getInstance(context: Context, serviceComponent: ComponentName) =
            instance ?: synchronized(this) {
                instance ?: MediaServiceConnection(context, serviceComponent).also {
                    instance = it
                }
            }
    }
}