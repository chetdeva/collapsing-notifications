package com.fueled.collapsingnotifications

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fueled.collapsingnotifications.dagger.Injector
import com.fueled.collapsingnotifications.notification.CollapsingNotificationManager
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var collapsingNotificationManager: CollapsingNotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Injector.getAppComponent(applicationContext).inject(this)

        intent?.extras?.let {
            collapsingNotificationManager.clearNotificationsToCollapse(intent.extras)
        }
    }
}