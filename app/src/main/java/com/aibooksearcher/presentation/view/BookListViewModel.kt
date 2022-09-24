package com.aibooksearcher.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aibooksearcher.data.repository.ApiRepository
import com.aibooksearcher.presentation.state.BookUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val repository: ApiRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<BookUiState> = MutableStateFlow(BookUiState())
    val uiState: StateFlow<BookUiState> = _uiState.asStateFlow()

    fun getBookList(keyword: String) {
        viewModelScope.launch {
            try {
                val bookList = repository.getBookList(
                    keyword,
                    100,
                    1
                )
                _uiState.update {
                    it.copy(
                        keyword = keyword,
                        bookList = bookList.items,
                        bookTotalCount = bookList.items.size
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(message = "책을 검색할 수 없습니다.\n잠시 뒤 다시 시도해주세요.")
                }
            }
        }
    }
}