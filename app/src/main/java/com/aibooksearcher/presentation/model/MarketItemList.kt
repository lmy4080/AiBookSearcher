package com.aibooksearcher.presentation.model

data class MarketItemList(
    val total: Int,
    val start: Int,
    val display: Int,
    val items: ArrayList<MarketItem>
)

data class MarketItem(
    val title: String,
    val link: String,
    val image: String,
    var lprice: String,
)
