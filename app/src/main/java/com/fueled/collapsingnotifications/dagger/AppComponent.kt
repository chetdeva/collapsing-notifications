package com.fueled.collapsingnotifications.dagger

import android.app.Application
import com.fueled.collapsingnotifications.CollapsingNotificationsApplication
import com.fueled.collapsingnotifications.MainActivity
import com.fueled.collapsingnotifications.fcm.AppFirebaseInstanceIdService
import com.fueled.collapsingnotifications.fcm.AppFirebaseMessagingService

import dagger.Component
import com.fueled.collapsingnotifications.notification.NotificationDismissTracker
import dagger.BindsInstance


/**
 * Created by chetansachdeva on 12/10/17.
 */

@PerApplication
@Component(modules = arrayOf(AppModule::class, NotificationModule::class))
interface AppComponent {

    fun inject(application: CollapsingNotificationsApplication)

    fun inject(appFirebaseInstanceIDService: AppFirebaseInstanceIdService)

    fun inject(appFirebaseMessagingService: AppFirebaseMessagingService)

    fun inject(notificationDismissTracker: NotificationDismissTracker)

    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
