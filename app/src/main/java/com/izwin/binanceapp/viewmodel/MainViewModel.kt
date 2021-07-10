package com.izwin.binanceapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.izwin.binanceapp.model.BidsAndAsksModel
import com.izwin.binanceapp.network.BinanceRetrofitClient
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {
    val bidsAndAsksModel = MutableLiveData<BidsAndAsksModel>()
    var isRequestsSending = false

    private fun getDepth(){
        runBlocking {
            async {
                bidsAndAsksModel.postValue(BinanceRetrofitClient.create()!!.getDepth("BNBBTC"))
            }.await()
        }
    }
    fun startRequestSending(interval: Int){
        isRequestsSending = true
        viewModelScope.launch {
            while(isRequestsSending){
                getDepth()
                delay(interval.toLong())
            }
        }
    }
    fun stopRequestSending(){
        isRequestsSending = false
    }

}