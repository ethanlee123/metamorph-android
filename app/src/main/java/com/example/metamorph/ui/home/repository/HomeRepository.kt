package com.example.metamorph.ui.home.repository

import com.example.metamorph.ui.home.model.OrderDetailsByIdResponse
import com.example.metamorph.ui.home.model.WebOrderParams
import com.example.metamorph.ui.home.model.WebOrderResponse
import com.example.metamorph.ui.home.network.RetrofitInstance
import retrofit2.Response

class HomeRepository {

    suspend fun getAvailableWebOrders(webOrderParams: WebOrderParams): Response<List<WebOrderResponse>> {
        return RetrofitInstance.api.getAvailableWebOrders(webOrderParams)
    }

    suspend fun getOrderDetailsById(
        orderNo: String
    ): Response<OrderDetailsByIdResponse> {
        return RetrofitInstance.api.getOrderDetailsById(orderNo)
    }

}