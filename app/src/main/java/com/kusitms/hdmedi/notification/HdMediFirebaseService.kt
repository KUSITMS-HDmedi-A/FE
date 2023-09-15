package com.kusitms.hdmedi.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.kusitms.hdmedi.MainActivity
import com.kusitms.hdmedi.R

class HdMediFirebaseService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(javaClass.name, "onNewToken: ${token} ")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        Log.d(javaClass.name, "onMessageReceived: ${message.from} ")
        message.notification?.let {
            showNotification(messageTitle = it.title ?: "", messageBody = it.body ?: "")
        }
    }

    private fun showNotification(messageTitle: String, messageBody: String) {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notificationID = (System.currentTimeMillis() / 7).toInt() // 고유 ID 지정

        createNotificationChannel(notificationManager)
        val intent = Intent(this@HdMediFirebaseService, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        val pendingIntent = PendingIntent.getActivity(
            this@HdMediFirebaseService,
            notificationID,
            intent,
            PendingIntent.FLAG_MUTABLE
        )
        notificationManager.notify(
            notificationID,
            notificationBuilder(messageTitle, messageBody, pendingIntent)
        )

    }

    private fun notificationBuilder(title: String, body: String, pendingIntent: PendingIntent) =
        NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .build()


    private fun createNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                enableLights(true)
                enableVibration(true)
                description = CHANNEL_DESCRIPTION
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        private const val CHANNEL_NAME = "HdMediNotification"
        private const val CHANNEL_DESCRIPTION = "Channel For HdMedi Notification"
        private const val CHANNEL_ID = "fcm_default_channel"
    }
}