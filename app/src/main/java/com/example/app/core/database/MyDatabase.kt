package com.example.app.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.app.core.database.dao.SearchHistoryDao
import com.example.app.core.database.dao.SongDao
import com.example.app.core.database.model.SearchHistoryEntity
import com.example.app.core.database.model.SongEntity


@Database(
    entities = [
        SongEntity::class,
        SearchHistoryEntity::class,
               ],
    version = 2,
    exportSchema = true,
)

abstract class MyDatabase : RoomDatabase(){
    abstract fun songDao(): SongDao
    abstract fun searchHistoryDao(): SearchHistoryDao

}