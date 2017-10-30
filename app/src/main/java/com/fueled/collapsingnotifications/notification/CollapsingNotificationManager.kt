package com.fueled.collapsingnotifications.notification

import android.os.Bundle
import com.fueled.collapsingnotifications.core.PushNotification
import com.fueled.collapsingnotifications.data.CollapsingNotificationStore
import com.fueled.collapsingnotifications.util.MultiValuedMap
import dagger.Reusable
import javax.inject.Inject

/**
 * Copyright (c) 2017 Fueled. All rights reserved.
 *
 * @author chetansachdeva on 23/09/17
 */

@Reusable
class CollapsingNotificationManager @Inject
constructor(private val collapsingNotificationStore: CollapsingNotificationStore) {

    private val collapsingNotifications = getCollapsingNotifications()

    /**
     * get notifications to collapse
     */
    fun getNotificationsToCollapse(data: Map<String, String>): List<Int>? {
        if (PushNotification.hasNotificationIdAndCollapseKey(data)) {

            val type = data[PushNotification.NOTIFICATION_COLLAPSE_KEY] ?: ""
            val id = data[PushNotification.NOTIFICATION_ID]?.toInt() ?: 0

            collapsingNotifications.put(type, id)
            putCollapsingNotifications(collapsingNotifications)

            return collapsingNotifications.getValues(type)
        }
        return null
    }

    /**
     * clear notifications to collapse
     * @return true if notifications were cleared
     */
    fun clearNotificationsToCollapse(data: Bundle): Boolean {
        if (PushNotification.hasNotificationKeyAndIds(data)) {

            val notificationKey = data.getString(PushNotification.NOTIFICATION_KEY) ?: ""
            val notificationIds = data.getIntegerArrayList(PushNotification.NOTIFICATION_IDS) ?: ArrayList()

            collapsingNotifications.removeValues(notificationKey, notificationIds)
            putCollapsingNotifications(collapsingNotifications)

            return true
        }
        return false
    }

    private fun getCollapsingNotifications() = collapsingNotificationStore.collapsingNotifications

    private fun putCollapsingNotifications(collapsingNotifications: MultiValuedMap<String, Int>) {
        collapsingNotificationStore.collapsingNotifications = collapsingNotifications
    }
}