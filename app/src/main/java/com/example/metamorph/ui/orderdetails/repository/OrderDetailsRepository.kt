package com.example.metamorph.ui.orderdetails.repository

import com.example.metamorph.model.OrderDetailsByIdParams
import com.example.metamorph.model.OrderDetailsByIdResponse
import com.example.metamorph.ui.home.network.RetrofitInstance
import retrofit2.Response

class OrderDetailsRepository {

    suspend fun getOrderDetailsById(
        orderDetailsByIdParams: OrderDetailsByIdParams
    ): Response<OrderDetailsByIdResponse> {
        return RetrofitInstance.api.getOrderDetailsById(orderDetailsByIdParams)
    }
}