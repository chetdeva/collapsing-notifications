package com.fueled.collapsingnotifications.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import com.fueled.collapsingnotifications.core.NotificationBuilder
import com.fueled.collapsingnotifications.core.NotificationItemResolver
import com.fueled.collapsingnotifications.core.PushNotification
import dagger.Reusable
import javax.inject.Inject

/**
 * Copyright (c) 2017 Fueled. All rights reserved.
 * PushNotification interface provides methods for push(), show() and hide() notifications.
 * createChannel() is important for Android O and above.
 *
 * @author chetansachdeva on 04/09/17
 */

@Reusable
class AppPushNotification @Inject
constructor(private val notificationManager: NotificationManager,
            private val resolver: NotificationItemResolver,
            private val notificationBuilder: NotificationBuilder,
            private val collapsingNotificationManager: CollapsingNotificationManager) : PushNotification {

    override fun push(context: Context, data: Map<String, String>) {
        val item = resolver.resolve(context, data, collapsingNotificationManager.getNotificationsToCollapse(data))

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) {
            createChannel(context, item.channel())
        }
        if (item.shouldCollapse()) {
            item.notificationsIdsToCollapse()?.let { clearNotifications(it) }
        }
        show(item.id(), notificationBuilder.build(context, item))
    }

    override fun show(notificationId: Int, notification: Notification) {
        notificationManager.notify(notificationId, notification)
    }

    override fun hide(notificationId: Int) {
        notificationManager.cancel(notificationId)
    }

    private fun clearNotifications(notificationIds: List<Int>) {
        for (notificationId in notificationIds) {
            hide(notificationId)
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun createChannel(context: Context, channel: AppNotificationChannel) {
        val channelTitle = context.getString(channel.titleRes)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val notificationChannel = NotificationChannel(channel.channelId, channelTitle, importance)
        notificationChannel.setShowBadge(true)
        notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        notificationManager.createNotificationChannel(notificationChannel)
    }
}