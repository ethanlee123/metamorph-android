package com.example.metamorph.ui.home.repository

import com.example.metamorph.model.WebOrderParams
import com.example.metamorph.model.WebOrderResponse
import com.example.metamorph.ui.home.network.RetrofitInstance
import retrofit2.Response

class HomeRepository {

    suspend fun getAvailableWebOrders(webOrderParams: WebOrderParams): Response<List<WebOrderResponse>> {
        return RetrofitInstance.api.getAvailableWebOrders(webOrderParams)
    }
}