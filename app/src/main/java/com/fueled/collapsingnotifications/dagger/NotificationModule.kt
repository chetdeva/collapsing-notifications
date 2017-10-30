package com.fueled.collapsingnotifications.dagger

import android.app.Application
import android.app.NotificationManager
import android.content.Context
import com.fueled.collapsingnotifications.notification.CollapsingNotificationManager
import com.fueled.collapsingnotifications.data.CollapsingNotificationStore
import com.fueled.collapsingnotifications.core.NotificationBuilder
import com.fueled.collapsingnotifications.core.NotificationItemResolver
import com.fueled.collapsingnotifications.core.PreferenceManager
import com.fueled.collapsingnotifications.core.PushNotification
import com.fueled.collapsingnotifications.notification.AppNotificationBuilder
import com.fueled.collapsingnotifications.notification.AppPushNotification
import com.fueled.collapsingnotifications.notification.AppNotificationItemResolver
import dagger.Module
import dagger.Provides

/**
 * Created by chetansachdeva on 19/10/17.
 */

@Module
class NotificationModule {

    @Provides
    @PerApplication
    fun provideNotificationManager(application: Application): NotificationManager {
        return application.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    @Provides
    @PerApplication
    fun provideNotificationItemResolver(): NotificationItemResolver {
        return AppNotificationItemResolver()
    }

    @Provides
    @PerApplication
    fun provideNotificationBuilder(): NotificationBuilder {
        return AppNotificationBuilder()
    }

    @Provides
    @PerApplication
    fun providePushNotification(notificationManager: NotificationManager,
                                resolver: NotificationItemResolver,
                                notificationBuilder: NotificationBuilder,
                                collapsingNotificationManager: CollapsingNotificationManager): PushNotification {
        return AppPushNotification(notificationManager, resolver, notificationBuilder, collapsingNotificationManager)
    }

    @Provides
    @PerApplication
    fun provideCollapsingNotificationStore(preferenceManager: PreferenceManager): CollapsingNotificationStore {
        return CollapsingNotificationStore(preferenceManager)
    }

}
