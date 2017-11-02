package com.fueled.collapsingnotifications.notification

import android.app.PendingIntent
import android.content.Context
import android.media.RingtoneManager
import com.fueled.collapsingnotifications.R
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.TaskStackBuilder
import com.fueled.collapsingnotifications.MainActivity
import com.fueled.collapsingnotifications.core.PushNotification
import com.fueled.collapsingnotifications.core.PushNotificationItem

/**
 * Copyright (c) 2017 Fueled. All rights reserved.
 * PushNotificationItem interface defines title, message, smallIcon, pendingIntent, deleteIntent etc.
 *
 * @author chetansachdeva on 04/09/17
 */

open class DefaultNotificationItem(val context: Context,
                              val data: Map<String, String>) : PushNotificationItem {

    /**
     * id of notification @{@link RemoteMessage#getMessageId()#hashCode()}
     * used for notify/cancel notification
     * @return  notification id
     */
    override fun id() = data[PushNotification.NOTIFICATION_ID]?.toInt() ?: 0

    /**
     * id of notification @{@link RemoteMessage#getMessageId()}
     * @return  notification message id
     */
    override fun messageId() = data[PushNotification.NOTIFICATION_MESSAGE_ID] ?: ""

    /**
     * type of notification @{@link RemoteMessage#getCollapseKey()}
     * @return  notification type
     */
    override fun type() = data[PushNotification.NOTIFICATION_COLLAPSE_KEY] ?: ""

    /**
     * notification channel based on android_channel_id
     * @return  notification channel
     */
    override fun channel() = AppNotificationChannel.DefaultChannel()

    /**
     * title of notification based on data.title
     * @return  notification title
     */
    override fun title() = data[PushNotification.NOTIFICATION_TITLE] ?: context.getString(R.string.app_name)

    /**
     * message of notification based on data.body
     * @return  notification message
     */
    override fun message() = data[PushNotification.NOTIFICATION_BODY] ?: ""

    /**
     * small icon of notifications
     * @return  notification small icon
     */
    override fun smallIcon() = R.mipmap.ic_launcher

    /**
     * sound of notification
     * @return  notification sound
     */
    override fun sound() = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

    /**
     * notification actions for notification buttons with PendingIntents
     * @return  notification actions
     */
    override fun actions(): List<NotificationCompat.Action>? = null

    /**
     * PendingIntent of notification for tap to redirect to Activity
     * @return  notification PendingIntent
     */
    override fun pendingIntent(): PendingIntent? = null

    /**
     * Delete PendingIntent of notification for dismiss
     * @return  notification PendingIntent
     */
    override fun deleteIntent(): PendingIntent? = null

    /**
     * notification ids to collapse used to
     * 1. Clear notification ids from SharedPreferences
     * 2. Clear notification from notification tray
     * @return  notification ids
     */
    override fun notificationsIdsToCollapse(): List<Int>? = null

    /**
     * Checks if notification should collapse based on size of list of notifications to collapse
     * @return  if notification should collapse
     */
    override fun shouldCollapse(): Boolean = false

    protected fun getTaskStackBuilder(resultIntent: Intent): TaskStackBuilder {
        val stackBuilder = TaskStackBuilder.create(context)
        stackBuilder.addParentStack(MainActivity::class.java)
        stackBuilder.addNextIntent(resultIntent)
        return stackBuilder
    }
}
