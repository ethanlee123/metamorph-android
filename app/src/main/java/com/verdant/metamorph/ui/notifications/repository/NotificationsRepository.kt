package com.verdant.metamorph.ui.notifications.repository

import com.verdant.metamorph.model.NotificationRowResponse
import com.verdant.metamorph.network.MetaMorphRetroFitInstance
import retrofit2.Response


class NotificationsRepository {

    suspend fun fetchPushNotifications(): Response<List<NotificationRowResponse>> {
        return MetaMorphRetroFitInstance.api.getReceivedPushNotifications()
    }
}