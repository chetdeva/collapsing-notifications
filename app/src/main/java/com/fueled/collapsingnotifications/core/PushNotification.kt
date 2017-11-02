package com.fueled.collapsingnotifications.core

import android.app.Notification
import android.content.Context
import android.os.Bundle
import com.fueled.collapsingnotifications.BuildConfig
import com.fueled.collapsingnotifications.notification.AppNotificationChannel
import com.google.firebase.messaging.RemoteMessage

interface PushNotification {

    fun push(context: Context, data: Map<String, String>)

    fun notify(notificationId: Int, notification: Notification)

    fun cancel(notificationId: Int)

    fun createChannel(context: Context, channel: AppNotificationChannel)

    companion object {

        val FIREBASE_APP_ID = BuildConfig.APPLICATION_ID
        val NOTIFICATION_KEY = FIREBASE_APP_ID + ".notificationKey"
        val NOTIFICATION_ID = FIREBASE_APP_ID + ".notificationId"
        val NOTIFICATION_IDS = FIREBASE_APP_ID + ".notificationIds"

        val NOTIFICATION_CHANNEL_ID = "android_channel_id"
        val NOTIFICATION_MESSAGE_ID = "message_id"
        val NOTIFICATION_CHANNEL_DEFAULT = "channel_default"
        val NOTIFICATION_CHANNEL_EMPTY = "channel_empty"
        val NOTIFICATION_ID_SERVICES = "services"
        val NOTIFICATION_CLICK_ACTION = "click_action"
        val NOTIFICATION_BODY = "body"
        val NOTIFICATION_TITLE = "title"
        val NOTIFICATION_CUSTOM_URL = "custom_url"
        val NOTIFICATION_COLLAPSE_KEY = "collapse_key"

        val NOTIFICATION_TYPE_A = "type_a"
        val NOTIFICATION_TYPE_B = "type_b"

        fun getRemoteData(remoteMessage: RemoteMessage): Map<String, String> {
            val remoteData = HashMap<String, String>()
            if (!remoteMessage.messageId.isNullOrEmpty()) {
                remoteData.put(PushNotification.NOTIFICATION_ID, remoteMessage.messageId.hashCode().toString())
                remoteData.put(PushNotification.NOTIFICATION_MESSAGE_ID, remoteMessage.messageId)
            }
            if (!remoteMessage.collapseKey.isNullOrEmpty() &&
                    !remoteMessage.collapseKey.equals(PushNotification.FIREBASE_APP_ID, ignoreCase = true)) {
                remoteData.put(PushNotification.NOTIFICATION_COLLAPSE_KEY, remoteMessage.collapseKey)
            }
            // Check if remoteMessage contains a notification payload.
            if (remoteMessage.notification != null) {
                println("Message Notification: " + remoteMessage.notification.toString())
                remoteData.put(PushNotification.NOTIFICATION_TITLE, remoteMessage.notification.title ?: "")
                remoteData.put(PushNotification.NOTIFICATION_BODY, remoteMessage.notification.body ?: "")
            }

            if (remoteMessage.data != null && remoteMessage.data.isNotEmpty()) {
                println("Message Data: " + remoteMessage.data.toString())
                for ((key, value) in remoteMessage.data) {
                    remoteData.put(key, value)
                }
            }
            return remoteData
        }

        fun hasNotificationIdAndCollapseKey(data: Map<String, String>): Boolean {
            return data.containsKey(NOTIFICATION_ID) &&
                    data.containsKey(NOTIFICATION_COLLAPSE_KEY) &&
                    !data[NOTIFICATION_ID].isNullOrEmpty() &&
                    !data[NOTIFICATION_COLLAPSE_KEY].isNullOrEmpty()
        }

        fun hasNotificationKeyAndIds(data: Bundle): Boolean {
            return data.containsKey(PushNotification.NOTIFICATION_KEY) &&
                    data.containsKey(PushNotification.NOTIFICATION_IDS) &&
                    !data.getString(PushNotification.NOTIFICATION_KEY).isNullOrEmpty() &&
                    !data.getIntegerArrayList(PushNotification.NOTIFICATION_IDS).isEmpty()
        }
    }
}