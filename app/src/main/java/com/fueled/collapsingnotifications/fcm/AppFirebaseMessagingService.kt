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
        pushNotification.push(applicationContext, PushNotification.getRemoteData(remoteMessage))
    }
}