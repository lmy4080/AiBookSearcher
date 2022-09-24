package com.aibooksearcher.presentation.model

import java.io.Serializable

data class BookList(
    val total: Int,
    val start: Int,
    val display: Int,
    val items: ArrayList<Book>
)

data class Book(
    val title: String,
    val link: String,
    val image: String,
    val author: String,
    val discount: String,
    val publisher: String,
    val pubdate: String,
    val isbn: String,
    val description: String,
    var isBooked: Boolean
): Serializable
