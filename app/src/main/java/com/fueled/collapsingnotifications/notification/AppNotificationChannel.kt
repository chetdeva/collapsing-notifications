package com.fueled.collapsingnotifications.notification

import android.support.annotation.StringRes
import com.fueled.collapsingnotifications.R
import com.fueled.collapsingnotifications.core.PushNotification

sealed class AppNotificationChannel(val channelId: String, @StringRes val titleRes: Int) {

    class DefaultChannel : AppNotificationChannel(PushNotification.NOTIFICATION_CHANNEL_DEFAULT, R.string.title_requests)

    class EmptyChannel : AppNotificationChannel(PushNotification.NOTIFICATION_CHANNEL_EMPTY, 0)
}