package com.izwin.binanceapp.network

import com.izwin.binanceapp.model.BidsAndAsksModel
import retrofit2.http.GET
import retrofit2.http.Query

interface BinanceService {
    @GET("depth")
    suspend fun getDepth(@Query("symbol") symbol: String) : BidsAndAsksModel
}