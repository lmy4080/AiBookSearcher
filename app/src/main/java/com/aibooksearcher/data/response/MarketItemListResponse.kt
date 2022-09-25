package com.aibooksearcher.data.response

data class MarketItemListResponse(
    val total: Int,
    val start: Int,
    val display: Int,
    val items: ArrayList<MarketItemResponse>
)

data class MarketItemResponse(
    val title: String,
    val link: String,
    val image: String,
    val lprice: String,
)
