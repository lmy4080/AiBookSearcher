package com.aibooksearcher.data.datasource

import com.aibooksearcher.data.ApiService
import com.aibooksearcher.mapper.toPresentation
import com.aibooksearcher.presentation.model.BookList
import com.aibooksearcher.presentation.model.MarketItemList
import javax.inject.Inject

class ApiDataSourceImpl @Inject constructor(
    private val apiService: ApiService
): ApiDataSource {

    override suspend fun getBookList(query: String, display: Int, start: Int): BookList {
        return apiService.getBookList(
            query,
            display,
            start
        ).toPresentation()
    }

    override suspend fun getMarketItemList(query: String, display: Int, start: Int): MarketItemList {
        return apiService.getMarketItemList(
            query,
            display,
            start
        ).toPresentation()
    }
}