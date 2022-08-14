package com.verdant.metamorph.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.verdant.metamorph.model.WebOrderParams
import com.verdant.metamorph.model.WebOrderResponse
import com.verdant.metamorph.ui.home.repository.HomeRepository
import kotlinx.coroutines.launch


class HomeViewModel(private val repository: HomeRepository) : ViewModel() {
    var webOrderResponse: MutableLiveData<List<WebOrderResponse>> = MutableLiveData()

    fun getWebOrders(webOrderParams: WebOrderParams) {
        viewModelScope.launch {
            val response = repository.getAvailableWebOrders(webOrderParams)
            webOrderResponse.value = response.body()
        }
    }
}