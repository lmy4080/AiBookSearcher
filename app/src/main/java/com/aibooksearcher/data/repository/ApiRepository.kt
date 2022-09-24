package com.aibooksearcher.data.repository

import com.aibooksearcher.data.local.entity.BookEntity
import com.aibooksearcher.presentation.model.Book
import com.aibooksearcher.presentation.model.BookList

interface ApiRepository {

    suspend fun getBookListFromNetwork(
        query: String,
        display: Int,
        start: Int,
    ): BookList

    suspend fun getBookListFromCache(): List<Book>

    suspend fun insertBook(
        bookEntity: BookEntity
    )

    suspend fun deleteBook(
        isbn: String
    )
}