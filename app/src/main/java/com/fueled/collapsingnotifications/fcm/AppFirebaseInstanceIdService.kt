package com.fueled.collapsingnotifications.fcm

import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

/**
 * Created by chetansachdeva on 12/10/17.
 */
class AppFirebaseInstanceIdService : FirebaseInstanceIdService() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onTokenRefresh() {
        val token = FirebaseInstanceId.getInstance().token
        println("Refreshed Token: " + token)
    }
}