package com.aibooksearcher.data.repository

import com.aibooksearcher.data.datasource.ApiDataSource
import com.aibooksearcher.data.local.dao.BookDao
import com.aibooksearcher.data.local.entity.BookEntity
import com.aibooksearcher.mapper.toPresentation
import com.aibooksearcher.presentation.model.Book
import com.aibooksearcher.presentation.model.BookList
import com.aibooksearcher.presentation.model.MarketItemList
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiDataSource: ApiDataSource,
    private val bookDao: BookDao
): ApiRepository {

    override suspend fun getBookListFromNetwork(query: String, display: Int, start: Int): BookList {
        return apiDataSource.getBookList(
            query,
            display,
            start
        )
    }

    override suspend fun getBookListFromCache(): List<Book> {
        return bookDao.getAll().map { it.toPresentation() }
    }

    override suspend fun insertBook(bookEntity: BookEntity) {
        bookDao.insertBook(bookEntity)
    }

    override suspend fun deleteBook(isbn: String) {
        bookDao.deleteComment(isbn)
    }

    override suspend fun getMarketItemList(query: String, display: Int, start: Int): MarketItemList {
        return apiDataSource.getMarketItemList(
            query,
            display,
            start
        )
    }
}