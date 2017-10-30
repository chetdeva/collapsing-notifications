package com.fueled.collapsingnotifications.core

import android.app.Notification
import android.content.Context

interface NotificationBuilder {
    fun build(context: Context, item: PushNotificationItem): Notification
}