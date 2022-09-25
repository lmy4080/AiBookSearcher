package com.aibooksearcher.presentation.state

import com.aibooksearcher.presentation.model.MarketItem

data class ShoppingUiState(
    val keyword: String? = null,
    val shoppingTotalCount: Int? = null,
    val marketItemList: List<MarketItem>? = null,
    val message: String? = null
)