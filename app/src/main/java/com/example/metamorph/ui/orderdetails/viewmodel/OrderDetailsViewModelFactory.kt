package com.example.metamorph.ui.orderdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.metamorph.ui.orderdetails.repository.OrderDetailsRepository

class OrderDetailsViewModelFactory(
    private val repository: OrderDetailsRepository
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OrderDetailsViewModel(repository) as T
    }
}