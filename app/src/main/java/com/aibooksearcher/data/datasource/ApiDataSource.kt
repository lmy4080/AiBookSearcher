package com.aibooksearcher.data.datasource

import com.aibooksearcher.data.response.BookListResponse

interface ApiDataSource {

    suspend fun getBookList(
        query: String,
        display: Int,
        start: Int,
    ): BookListResponse
}