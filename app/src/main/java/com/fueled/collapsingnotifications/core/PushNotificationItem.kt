package com.fueled.collapsingnotifications.core

import android.app.PendingIntent
import android.net.Uri
import com.fueled.collapsingnotifications.notification.AppNotificationChannel

/**
 * Copyright (c) 2017 Fueled. All rights reserved.
 *
 * @author chetansachdeva on 04/09/17
 */

interface PushNotificationItem {

    fun id(): Int

    fun messageId(): String

    fun type(): String

    fun channel(): AppNotificationChannel

    fun title(): String

    fun message(): String

    fun notificationsIdsToCollapse(): List<Int>?

    fun smallIcon(): Int

    fun sound(): Uri

    fun pendingIntent(): PendingIntent

    fun deleteIntent(): PendingIntent

    fun shouldCollapse(): Boolean
}
