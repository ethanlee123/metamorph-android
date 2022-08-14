package com.verdant.metamorph.ui.orderdetails.repository

import com.verdant.metamorph.model.OrderDetailsByIdParams
import com.verdant.metamorph.model.OrderDetailsByIdResponse
import com.verdant.metamorph.ui.home.network.RetrofitInstance
import retrofit2.Response

class OrderDetailsRepository {

    suspend fun getOrderDetailsById(
        orderDetailsByIdParams: OrderDetailsByIdParams
    ): Response<OrderDetailsByIdResponse> {
        return RetrofitInstance.api.getOrderDetailsById(orderDetailsByIdParams)
    }
}