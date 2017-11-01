package com.fueled.collapsingnotifications.notification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.fueled.collapsingnotifications.MainActivity
import com.fueled.collapsingnotifications.R
import com.fueled.collapsingnotifications.core.PushNotification

/**
 * Copyright (c) 2017 Fueled. All rights reserved.
 *
 * @author chetansachdeva on 01/11/17
 */

class AppNotificationItem(context: Context, data: Map<String, String>, private val idsToCollapse: List<Int>?)
    : DefaultNotificationItem(context, data) {

    override fun title(): String {
        return if (shouldCollapse())
            getCollapsedTitle(type(), context)
        else super.title()
    }

    override fun message(): String {
        return if (shouldCollapse())
            getCollapsedMessage(type(), notificationsIdsToCollapse()?.size ?: 0, context)
        else super.message()
    }

    override fun pendingIntent(): PendingIntent? {
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra(PushNotification.NOTIFICATION_MESSAGE_ID, messageId())
        putNotificationIds(intent)
        val requestCode = System.currentTimeMillis().toInt()
        return getTaskStackBuilder(intent).getPendingIntent(requestCode, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    override fun deleteIntent(): PendingIntent? {
        val intent = Intent(context, NotificationDismissTracker::class.java)
        putNotificationIds(intent)
        return PendingIntent.getBroadcast(context, id(), intent, PendingIntent.FLAG_ONE_SHOT)
    }

    private fun putNotificationIds(intent: Intent) {
        if (type().isNotBlank()) {
            intent.putExtra(PushNotification.NOTIFICATION_KEY, type())
            intent.putExtra(PushNotification.NOTIFICATION_IDS, ArrayList(idsToCollapse))
        }
    }

    override fun notificationsIdsToCollapse() = idsToCollapse

    override fun shouldCollapse() = idsToCollapse?.let { it.size > 1 } ?: false

    /**
     * get title for collapsed notification
     */
    private fun getCollapsedTitle(type: String, context: Context) =
            when (type) {
                PushNotification.NOTIFICATION_TYPE_A -> context.getString(R.string.type_a_collapsing_title)
                PushNotification.NOTIFICATION_TYPE_B -> context.getString(R.string.type_b_collapsing_title)
                else -> super.title()
            }

    /**
     * get message for collapsed notification
     */
    private fun getCollapsedMessage(type: String, size: Int, context: Context) =
            when (type) {
                PushNotification.NOTIFICATION_TYPE_A -> context.getString(R.string.type_a_collapsing_message, size)
                PushNotification.NOTIFICATION_TYPE_B -> context.getString(R.string.type_b_collapsing_message, size)
                else -> super.message()
            }
}
