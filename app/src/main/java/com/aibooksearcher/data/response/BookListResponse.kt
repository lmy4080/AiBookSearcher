package com.aibooksearcher.data.response

data class BookListResponse(
    val total: Int,
    val start: Int,
    val display: Int,
    val items: ArrayList<BookResponse>
)

data class BookResponse(
    val title: String,
    val link: String,
    val image: String,
    val author: String,
    val discount: String,
    val publisher: String,
    val pubdate: String,
    val isbn: String,
    val description: String,
)