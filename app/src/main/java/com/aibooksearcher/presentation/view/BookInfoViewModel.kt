package com.aibooksearcher.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aibooksearcher.data.repository.ApiRepository
import com.aibooksearcher.mapper.toEntity
import com.aibooksearcher.presentation.model.Book
import com.aibooksearcher.presentation.state.BookInfoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookInfoViewModel @Inject constructor(
    private val repository: ApiRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<BookInfoUiState> = MutableStateFlow(BookInfoUiState())
    val uiState: StateFlow<BookInfoUiState> = _uiState.asStateFlow()

    fun bookMark() {
        _uiState.value.book?.let {
            if(it.isBooked) {
                deleteBook(it.isbn)
            } else {
                insertBook(it)
            }
        }
    }

    private fun insertBook(book: Book) {
        viewModelScope.launch {
            repository.insertBook(book.toEntity())

            _uiState.update {
                it.copy(
                    book = it.book?.copy(
                        isBooked = true
                    )
                )
            }
        }
    }

    private fun deleteBook(isbn: String) {
        viewModelScope.launch {
            repository.deleteBook(isbn)

            _uiState.update {
                it.copy(
                    book = it.book?.copy(
                        isBooked = false
                    )
                )
            }
        }
    }

    fun getBook(book: Book) {
        viewModelScope.launch {
            val bookList = repository.getBookListFromCache()
            bookList.filter {
                it.isbn == book.isbn
            }.map {
                book.isBooked = true
            }

            _uiState.update {
                it.copy(
                    book = book
                )
            }
        }
    }
}