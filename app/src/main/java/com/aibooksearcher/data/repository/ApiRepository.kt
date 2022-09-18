package com.aibooksearcher.data.repository

import com.aibooksearcher.presentation.BookList

interface ApiRepository {

    suspend fun getBookList(
        query: String,
        display: Int,
        start: Int,
    ): BookList
}