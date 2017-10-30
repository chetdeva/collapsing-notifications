package com.fueled.collapsingnotifications.fcm

import com.fueled.collapsingnotifications.dagger.Injector
import com.fueled.collapsingnotifications.core.PushNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import javax.inject.Inject

/**
 * Created by chetansachdeva on 12/10/17.
 */
class AppFirebaseMessagingService : FirebaseMessagingService() {

    @Inject lateinit var pushNotification: PushNotification

    override fun onCreate() {
        Injector.getAppComponent(applicationContext).inject(this)
        super.onCreate()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        pushNotification.push(applicationContext, getRemoteData(remoteMessage))
    }

    private fun getRemoteData(remoteMessage: RemoteMessage): Map<String, String> {
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
}