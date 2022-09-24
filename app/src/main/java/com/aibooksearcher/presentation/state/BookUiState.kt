package com.aibooksearcher.presentation.state

import com.aibooksearcher.presentation.model.Book

data class BookUiState(
    val keyword: String? = null,
    val bookTotalCount: Int? = null,
    val bookList: List<Book>? = null,
    val message: String? = null
)