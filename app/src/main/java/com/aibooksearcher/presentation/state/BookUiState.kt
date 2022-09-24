package com.aibooksearcher.presentation.state

import com.aibooksearcher.presentation.model.Book

data class BookUiState(
    val keyword: String = "",
    val bookTotalCount: Int = 0,
    val bookList: List<Book> = listOf(),
    val message: String = ""
)