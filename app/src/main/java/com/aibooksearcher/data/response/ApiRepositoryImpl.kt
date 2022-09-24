package com.aibooksearcher.data.response

import com.aibooksearcher.data.datasource.ApiDataSource
import com.aibooksearcher.data.repository.ApiRepository
import com.aibooksearcher.mapper.toPresentation
import com.aibooksearcher.presentation.model.BookList
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiDataSource: ApiDataSource
): ApiRepository {

    override suspend fun getBookList(query: String, display: Int, start: Int): BookList {
        return apiDataSource.getBookList(
            query,
            display,
            start
        ).toPresentation()
    }
}