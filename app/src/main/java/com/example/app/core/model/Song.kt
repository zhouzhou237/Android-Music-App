package com.example.app.core.model

import android.net.Uri
import androidx.media3.common.MediaMetadata
import com.example.app.core.database.model.SongEntity
import com.example.app.util.Constant
import com.example.app.util.ResourceUtil
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

){
    fun toSongEntity():SongEntity{
        return SongEntity(
            id = id,
            title=title,
            uri = ResourceUtil.r2(uri),
            icon = icon,
            album =  album,
            artist = artist,
            genre = genre,
            lyricStyle = lyricStyle,
            lyric = lyric,
            trackNumber = trackNumber,
            totalTrackCount =totalTrackCount,
            playList = true,
        )
    }
}

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

fun MediaMetadata.Builder.from(data: SongEntity): MediaMetadata.Builder {
    setTitle(data.title)
    setDisplayTitle(data.title)
    setArtist(data.artist)
    setAlbumTitle(data.album)
    setGenre(data.genre)
    setGenre(data.genre)
    setTrackNumber(data.trackNumber)
    setTotalTrackCount(data.totalTrackCount)
    setIsBrowsable(false)
    setMediaType(MediaMetadata.MEDIA_TYPE_MUSIC)
    setIsPlayable(true)
    setArtworkUri(Uri.parse(ResourceUtil.r2(data.icon)))
    // The duration from the JSON is given in seconds, but the rest of the code works in
    // milliseconds. Here's where we convert to the proper units.
//    val durationMs = TimeUnit.SECONDS.toMillis(data.duration)
//    val bundle = Bundle()
//    bundle.putLong("durationMs", durationMs)
    return this
}