package com.verdant.metamorph.ui.orderdetails.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.verdant.metamorph.model.OrderDetailsByIdParams
import com.verdant.metamorph.model.OrderDetailsByIdResponse
import com.verdant.metamorph.ui.orderdetails.repository.OrderDetailsRepository
import kotlinx.coroutines.launch


class OrderDetailsViewModel(private val repository: OrderDetailsRepository) : ViewModel() {
    var orderDetails: MutableLiveData<OrderDetailsByIdResponse> = MutableLiveData()

    fun getOrderDetailsById(orderDetailsByIdParams: OrderDetailsByIdParams) {
        viewModelScope.launch {
            val response = repository.getOrderDetailsById(orderDetailsByIdParams)
            orderDetails.value = response.body()
        }
    }
}