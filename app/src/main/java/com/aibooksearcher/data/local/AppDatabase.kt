package com.aibooksearcher.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aibooksearcher.data.local.dao.BookDao
import com.aibooksearcher.data.local.entity.BookEntity

@Database(entities = [BookEntity::class], version = 5, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract val bookDao: BookDao

    companion object {
        const val DB_NAME = "aibookspeaker.db"
    }
}