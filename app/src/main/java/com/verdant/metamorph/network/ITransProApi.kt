package com.verdant.metamorph.network

import com.verdant.metamorph.model.OrderDetailsByIdParams
import com.verdant.metamorph.model.OrderDetailsByIdResponse
import com.verdant.metamorph.model.WebOrderParams
import com.verdant.metamorph.model.WebOrderResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ITransProApi {
    @Headers("Accept: application/json;charset=UTF-8")
    @POST("order/getWebOrders")
    suspend fun getAvailableWebOrders(
        @Body webOrderParams: WebOrderParams
    ): Response<List<WebOrderResponse>>

    @Headers("Accept: application/json;charset=UTF-8")
    @POST("order/getOrderDetailsById")
    suspend fun getOrderDetailsById(
        @Body orderDetailsByIdParams: OrderDetailsByIdParams
    ): Response<OrderDetailsByIdResponse>

    @POST("translator/login")
    fun login(@Body requestBody: RequestBody): Call<Any>
}