package com.aibooksearcher.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aibooksearcher.data.repository.ApiRepository
import com.aibooksearcher.presentation.state.BookMarkListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkListViewModel @Inject constructor(
    private val repository: ApiRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<BookMarkListUiState> = MutableStateFlow(BookMarkListUiState(emptyList()))
    val uiState: StateFlow<BookMarkListUiState> = _uiState.asStateFlow()

    fun getBookListFromCache() {
        viewModelScope.launch {
            val result = repository.getBookListFromCache()

            _uiState.update {
                it.copy(
                    bookList = result
                )
            }
        }
    }
}