package com.verdant.metamorph.network

import com.verdant.metamorph.model.NotificationRowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface IMetaMorphApi {
    @Headers("Accept: application/json;charset=UTF-8")
    @GET("received_push_notifications")
    suspend fun getReceivedPushNotifications(): Response<List<NotificationRowResponse>>
}