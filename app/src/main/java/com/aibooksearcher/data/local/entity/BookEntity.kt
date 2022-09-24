package com.aibooksearcher.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Book")
data class BookEntity(
    @PrimaryKey(autoGenerate = false)
    val isbn: String,
    val title: String,
    val link: String,
    val image: String,
    val author: String,
    val discount: String,
    val publisher: String,
    val pubdate: String,
    val description: String
)
