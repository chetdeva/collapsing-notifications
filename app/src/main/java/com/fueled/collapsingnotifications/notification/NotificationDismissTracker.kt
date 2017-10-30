package com.fueled.collapsingnotifications.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.fueled.collapsingnotifications.dagger.Injector
import javax.inject.Inject

/**
 * Created by chetansachdeva on 14/10/17.
 */

class NotificationDismissTracker : BroadcastReceiver() {

    @Inject lateinit var collapsingNotificationManager: CollapsingNotificationManager

    override fun onReceive(context: Context, intent: Intent?) {
        Injector.getAppComponent(context.applicationContext).inject(this);

        intent?.extras?.let {
            collapsingNotificationManager.clearNotificationsToCollapse(intent.extras)
        }
    }
}