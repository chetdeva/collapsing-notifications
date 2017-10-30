package com.fueled.collapsingnotifications.notification

import android.app.Notification
import android.content.Context
import android.support.v4.app.NotificationCompat
import com.fueled.collapsingnotifications.R
import com.fueled.collapsingnotifications.core.NotificationBuilder
import com.fueled.collapsingnotifications.core.PushNotification
import com.fueled.collapsingnotifications.core.PushNotificationItem

import javax.inject.Inject

/**
 * Copyright (c) 2017 Fueled. All rights reserved.
 * NotificationBuilder is an interface that provides a contract for making android.app.Notification
 * out of the type that was resolved by the resolver.
 *
 * @author chetansachdeva on 04/09/17
 */

class AppNotificationBuilder @Inject
constructor() : NotificationBuilder {

    override fun build(context: Context, item: PushNotificationItem): Notification {

        val title = if (item.shouldCollapse())
            getCollapsedTitle(item.type(), context)
        else item.title()

        val message = if (item.shouldCollapse())
            getCollapsedMessage(item.type(), item.notificationsIdsToCollapse()?.size ?: 0, context)
        else item.message()

        val builder = NotificationCompat.Builder(context, item.channel().channelId)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(item.smallIcon())
                .setContentTitle(title)
                .setTicker(message)
                .setContentText(message)
                .setSound(item.sound())
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setDeleteIntent(item.deleteIntent())
                .setContentIntent(item.pendingIntent())

        return builder.build()
    }

    /**
     * get title for collapsed notification
     */
    private fun getCollapsedTitle(type: String, context: Context) =
            when (type) {
                PushNotification.NOTIFICATION_TYPE_A -> context.getString(R.string.type_a_collapsing_title)
                PushNotification.NOTIFICATION_TYPE_B -> context.getString(R.string.type_b_collapsing_title)
                else -> null
            }

    /**
     * get message for collapsed notification
     */
    private fun getCollapsedMessage(type: String, size: Int, context: Context) =
            when (type) {
                PushNotification.NOTIFICATION_TYPE_A -> context.getString(R.string.type_a_collapsing_message, size)
                PushNotification.NOTIFICATION_TYPE_B -> context.getString(R.string.type_b_collapsing_message, size)
                else -> null
            }
}
