package com.izwin.binanceapp.model

class BidsAndAsksModel(
    var lastUpdateId: Int,
    val bids: List<List<String>>,
    val asks: List<List<String>>
)
