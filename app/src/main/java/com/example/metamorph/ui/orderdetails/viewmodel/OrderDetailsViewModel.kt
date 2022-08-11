package com.example.metamorph.ui.orderdetails.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metamorph.model.OrderDetailsByIdResponse
import com.example.metamorph.ui.orderdetails.repository.OrderDetailsRepository
import kotlinx.coroutines.launch

class OrderDetailsViewModel(private val repository: OrderDetailsRepository) : ViewModel() {
    var orderDetails: MutableLiveData<OrderDetailsByIdResponse> = MutableLiveData()

    fun getOrderDetailsById(orderNo: String) {
        viewModelScope.launch {
            val response = repository.getOrderDetailsById(orderNo)
            orderDetails.value = response.body()
        }
    }
}