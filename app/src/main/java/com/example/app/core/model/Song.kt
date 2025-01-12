package com.example.app.core.model

import com.example.app.util.Constant


/**
 * Songs

 */

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