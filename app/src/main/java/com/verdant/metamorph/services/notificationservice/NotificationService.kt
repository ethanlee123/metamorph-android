package com.verdant.metamorph.services.notificationservice

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.ktx.messaging
import com.verdant.metamorph.R

class NotificationService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d(TAG, "Message received")
        val title = message.notification?.title
        val body = message.notification?.body
        val CHANNEL_ID = "HEADS_UP_NOTIF"
        val CHANNEL_NAME = resources.getString(R.string.default_notification_channel_id)
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)

        val notificationBuilder = Notification.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)

        NotificationManagerCompat.from(this).notify(1, notificationBuilder.build())

        // TODO: When app is in foreground ->
        // notify notification fragment
        Log.d(TAG, "foreground: $title")
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "Refreshed token: $token")
    }

    fun subscribeToNewWebOrderTopic() {
        Firebase.messaging.subscribeToTopic(TOPIC_NEW_WEB_ORDERS)
            .addOnCompleteListener { task ->
                var msg = "Subscribed"
                if (!task.isSuccessful) {
                    msg = "Subscribe failed"
                }
                Log.d(TAG, msg)
            }
    }

    companion object {
        const val TAG = "Notification service"
        const val TOPIC_NEW_WEB_ORDERS = "weather"
    }
}