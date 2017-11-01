package com.fueled.collapsingnotifications.notification

import android.app.Notification
import android.content.Context
import android.support.v4.app.NotificationCompat
import com.fueled.collapsingnotifications.core.NotificationBuilder
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

        val builder = NotificationCompat.Builder(context, item.channel().channelId)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(item.smallIcon())
                .setContentTitle(item.title())
                .setTicker(item.message())
                .setContentText(item.message())
                .setSound(item.sound())
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setDeleteIntent(item.deleteIntent())
                .setContentIntent(item.pendingIntent())

        val actions = item.actions()
        if (actions != null && actions.isNotEmpty()) {
            for (action in actions) {
                builder.addAction(action)
            }
        }

        return builder.build()
    }
}
