package com.example.android.eggtimernotifications

import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.android.eggtimernotifications.util.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.d("Firebase", "Refreshed token: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        Log.d("Firebase", "From: ${remoteMessage.from}")

        remoteMessage.data.let {
            Log.d("Firebase", "Message data payload: " + remoteMessage.data)
        }

        val notificationManager = ContextCompat.getSystemService(
            applicationContext,
            NotificationManager::class.java
        ) as NotificationManager

        remoteMessage.notification?.let {
            Log.d("Firebase", "Message Notification Body: ${it.body}")
            notificationManager.sendNotification(it.body!!,applicationContext)
        }

    }
}