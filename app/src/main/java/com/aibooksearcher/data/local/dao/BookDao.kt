package com.aibooksearcher.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aibooksearcher.data.local.entity.BookEntity

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(vararg book: BookEntity)

    @Query("DELETE FROM Book WHERE isbn =:isbn")
    fun deleteComment(isbn: String)

    @Query("SELECT * FROM Book")
    fun getAll(): List<BookEntity>
}