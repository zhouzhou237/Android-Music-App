package com.example.app.core.database.model

import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.MimeTypes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.app.core.model.Song
import com.example.app.core.model.from
import com.example.app.util.Constant

/**
 * Music Database Model
 *
 * Mainly used to store playlists.
 *
 * Refer to [com.example.app.core.model.Song]
 */

@Entity(tableName = "song")
data class SongEntity(
    @PrimaryKey
    val id: String,

    val title: String,

    /**
     * Relative path of the music.
     *
     * Absolute path of the music scanned locally.
     * */

    val uri: String = "",
    val icon: String = "",
    val album: String = "",
    val artist: String = "",
    val genre: String = "",

    @ColumnInfo(name = "lyric_style")
    val lyricStyle: Int = Constant.VALUE0,
    val lyric: String = "",

    @ColumnInfo(name = "track_number")
    val trackNumber: Int = 1,

    @ColumnInfo(name = "total_track_count")
    val totalTrackCount: Int = 1,

    @ColumnInfo(name = "play_list")
    val playList: Boolean = false,

    /**
     * Source of the music.
     */

    val source: Byte = 0,
) {
    fun toSong(): Song {
        return Song(
            id = id,
            title = title,
            uri = uri,
            icon = icon,
            album = album,
            artist = artist,
            genre = genre,
            lyricStyle = lyricStyle,
            lyric = lyric,
            trackNumber = trackNumber,
            totalTrackCount = totalTrackCount,
        )
    }
}


internal fun SongEntity.asMediaItem() = MediaItem.Builder()
    .apply {
        setMediaId(id)
        setUri(uri)
        setMimeType(MimeTypes.AUDIO_MPEG)
        setMediaMetadata(
            MediaMetadata.Builder()
                .from(this@asMediaItem)
                .build()
        )
    }.build()