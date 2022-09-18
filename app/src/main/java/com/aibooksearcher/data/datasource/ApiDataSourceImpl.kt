package com.aibooksearcher.data.datasource

import com.aibooksearcher.data.ApiService
import com.aibooksearcher.data.response.BookListResponse
import javax.inject.Inject

class ApiDataSourceImpl @Inject constructor(
    val apiService: ApiService
): ApiDataSource {

    override suspend fun getBookList(query: String, display: Int, start: Int): BookListResponse {
        return apiService.getBookList(
            query,
            display,
            start
        )
    }
}