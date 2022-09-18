package com.aibooksearcher.data

import com.aibooksearcher.data.response.BookListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v1/search/book.json")
    suspend fun getBookList(
        @Query("query") query: String,
        @Query("display") display: Int,
        @Query("start") start: Int,
    ): BookListResponse
}