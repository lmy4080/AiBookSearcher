package com.aibooksearcher.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aibooksearcher.data.repository.ApiRepository
import com.aibooksearcher.presentation.state.ShoppingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val repository: ApiRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<ShoppingUiState> = MutableStateFlow(ShoppingUiState())
    val uiState: StateFlow<ShoppingUiState> = _uiState.asStateFlow()

    fun getMarketItemList(keyword: String) {
        viewModelScope.launch {
            try {
                val marketItemList = repository.getMarketItemList(
                    keyword,
                    100,
                    1
                )
                _uiState.update {
                    it.copy(
                        keyword = keyword,
                        marketItemList = marketItemList.items,
                        shoppingTotalCount = marketItemList.items.size
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(message = "관련 상품을 검색할 수 없습니다.\n잠시 뒤 다시 시도해주세요.")
                }
            }
        }
    }
}