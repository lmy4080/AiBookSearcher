package com.aibooksearcher.data.datasource

import com.aibooksearcher.presentation.model.BookList
import com.aibooksearcher.presentation.model.MarketItemList

interface ApiDataSource {

    suspend fun getBookList(
        query: String,
        display: Int,
        start: Int,
    ): BookList

    suspend fun getMarketItemList(
        query: String,
        display: Int,
        start: Int,
    ): MarketItemList
}