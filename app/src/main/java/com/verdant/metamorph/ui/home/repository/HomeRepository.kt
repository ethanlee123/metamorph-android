package com.verdant.metamorph.ui.home.repository

import com.verdant.metamorph.model.WebOrderParams
import com.verdant.metamorph.model.WebOrderResponse
import com.verdant.metamorph.network.RetrofitInstance
import retrofit2.Response

class HomeRepository {

    suspend fun getAvailableWebOrders(webOrderParams: WebOrderParams): Response<List<WebOrderResponse>> {
        return RetrofitInstance.api.getAvailableWebOrders(webOrderParams)
    }
}