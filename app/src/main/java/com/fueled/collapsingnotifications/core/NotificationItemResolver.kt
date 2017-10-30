package com.fueled.collapsingnotifications.core

import android.content.Context

interface NotificationItemResolver {
    fun resolve(context: Context, data: Map<String, String>, notificationsToCollapse: List<Int>?): PushNotificationItem
}