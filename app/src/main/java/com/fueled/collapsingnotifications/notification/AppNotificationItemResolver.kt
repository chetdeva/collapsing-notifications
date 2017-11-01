package com.fueled.collapsingnotifications.notification

import android.content.Context
import com.fueled.collapsingnotifications.core.NotificationItemResolver
import com.fueled.collapsingnotifications.core.PushNotification
import com.fueled.collapsingnotifications.core.PushNotificationItem

/**
 * Copyright (c) 2017 Fueled. All rights reserved.
 * NotificationItemResolver is an interface that provides a contract for resolving the received data into a type that will be used to build a displayable notification.
 *
 * @author chetansachdeva on 04/09/17
 */

class AppNotificationItemResolver : NotificationItemResolver {

    override fun resolve(context: Context, data: Map<String, String>, notificationsToCollapse: List<Int>?): PushNotificationItem {
        if (!data[PushNotification.NOTIFICATION_CHANNEL_ID].isNullOrBlank()) {
            when (data[PushNotification.NOTIFICATION_CHANNEL_ID]) {
                PushNotification.NOTIFICATION_CHANNEL_DEFAULT -> AppNotificationItem(context, data, notificationsToCollapse)
            }
        }
        return AppNotificationItem(context, data, notificationsToCollapse)
    }
}
