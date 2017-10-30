package com.fueled.collapsingnotifications.notification

import android.app.PendingIntent;
import android.content.Context;
import android.media.RingtoneManager
import com.fueled.collapsingnotifications.R
import android.content.Intent
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

class DefaultNotificationItem(val context: Context,
                              val data: Map<String, String>,
                              val idsToCollapse: List<Int>?) : PushNotificationItem {

    override fun id() = data[PushNotification.NOTIFICATION_ID]?.toInt() ?: 0

    override fun messageId() = data[PushNotification.NOTIFICATION_MESSAGE_ID] ?: ""

    override fun type() = data[PushNotification.NOTIFICATION_COLLAPSE_KEY] ?: ""

    override fun channel() = AppNotificationChannel.DefaultChannel()

    override fun title() = data[PushNotification.NOTIFICATION_TITLE] ?: context.getString(R.string.app_name)

    override fun message() = data[PushNotification.NOTIFICATION_BODY] ?: ""

    override fun notificationsIdsToCollapse() = idsToCollapse

    override fun smallIcon() = R.mipmap.ic_launcher

    override fun sound() = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

    override fun pendingIntent(): PendingIntent {
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra(PushNotification.NOTIFICATION_MESSAGE_ID, messageId())
        putNotificationIds(intent)
        val requestCode = System.currentTimeMillis().toInt()
        return getTaskStackBuilder(intent).getPendingIntent(requestCode, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun getTaskStackBuilder(resultIntent: Intent): TaskStackBuilder {
        val stackBuilder = TaskStackBuilder.create(context)
        stackBuilder.addParentStack(MainActivity::class.java)
        stackBuilder.addNextIntent(resultIntent)
        return stackBuilder
    }

    override fun deleteIntent(): PendingIntent {
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

    override fun shouldCollapse() = idsToCollapse?.let { it.size > 1 } ?: false

}
