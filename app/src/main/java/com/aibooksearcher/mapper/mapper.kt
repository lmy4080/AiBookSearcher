package com.aibooksearcher.mapper

import com.aibooksearcher.data.response.BookListResponse
import com.aibooksearcher.data.response.BookResponse
import com.aibooksearcher.presentation.model.Book
import com.aibooksearcher.presentation.model.BookList

fun BookListResponse.toPresentation() = BookList(
    total = total,
    start = start,
    display = display,
    items = items.map { it.toPresentation() } as ArrayList<Book>
)

fun BookResponse.toPresentation() = Book(
    title = title,
    link = link,
    image = image,
    author = author,
    discount = discount,
    publisher = publisher,
    pubdate = pubdate,
    isbn = isbn,
    description = description
)

