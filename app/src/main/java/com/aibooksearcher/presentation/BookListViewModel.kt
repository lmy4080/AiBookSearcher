package com.aibooksearcher.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aibooksearcher.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val repository: ApiRepository
): ViewModel() {

    fun getBookList(keyword: String) {
        viewModelScope.launch {
            repository.getBookList(
                keyword,
                100,
                1
            )
        }
    }
}