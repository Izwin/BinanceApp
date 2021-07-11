package com.izwin.binanceapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.izwin.binanceapp.model.BidsAndAsksModel
import com.izwin.binanceapp.network.BinanceRetrofitClient
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {
    val bidsAndAsksModel = MutableLiveData<BidsAndAsksModel>()
    val isLoading = MutableLiveData(true)
    private var symbol = "BTCUSDT"
    private var getDepthJob: Job? = null

    private fun getDepth(){
        viewModelScope.launch {
            bidsAndAsksModel.postValue(BinanceRetrofitClient.create()!!.getDepth(symbol))
            isLoading.postValue(false)
        }
    }
    fun startRequestSending(interval: Int){
        stopRequestSending()
        getDepthJob = viewModelScope.launch{
            while(true){
                getDepth()
                delay(interval.toLong())
            }
        }
    }
    fun stopRequestSending(){
        getDepthJob?.cancel()
    }
    fun changeSymbol(symbol: String){
        stopRequestSending()
        this.symbol = symbol
        getDepth()
        isLoading.postValue(true)
        startRequestSending(1500)
    }

}