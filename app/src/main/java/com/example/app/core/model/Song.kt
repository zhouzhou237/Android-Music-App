package com.example.app.core.model

import androidx.media3.common.MediaMetadata
import com.example.app.util.Constant
import kotlinx.serialization.Serializable


/**
 * Songs

 */
@Serializable
data class Song(
    /**
     * Unique ID for Songs
     */
    val id: String,

    /**
     * Songs's Title
     */
    val title: String,

    /**
     * Audio File
     */
    val uri: String,

    /**
     * Cover
     */
    val icon: String = "",

    /**
     * Album's name
     */
    val album: String,

    /**
     * Artist's name
     */
    val artist: String,

    /**
     * Songs's type
     */
    val genre: String,

    val lyricStyle: Int = Constant.VALUE0,
    val lyric: String = "",

    /**
     * TrackNumber
     *
     * begain with 1
     */
    val trackNumber: Int = 1,

    /**
     * Total number of songs on the album
     */
    val totalTrackCount: Int = 1,

)

fun MediaMetadata.Builder.from(data: Song): MediaMetadata.Builder {
    setTitle(data.title)
    setDisplayTitle(data.title)
    setArtist(data.artist)
    setAlbumTitle(data.album)
    setGenre(data.genre)
    setTrackNumber(data.trackNumber)
    setTotalTrackCount(data.totalTrackCount)
    setIsBrowsable(false)
    setMediaType(MediaMetadata.MEDIA_TYPE_MUSIC)
    setIsPlayable(true)
    // The duration from the JSON is given in seconds, but the rest of the code works in
    // milliseconds. Here's where we convert to the proper units.
//    val durationMs = TimeUnit.SECONDS.toMillis(data.duration)
//    val bundle = Bundle()
//    bundle.putLong("durationMs", durationMs)
    return this
}