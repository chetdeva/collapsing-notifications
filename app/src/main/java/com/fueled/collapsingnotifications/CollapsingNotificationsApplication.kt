package com.fueled.collapsingnotifications

import android.app.Application

import com.fueled.collapsingnotifications.dagger.AppComponent
import com.fueled.collapsingnotifications.dagger.AppModule
import com.fueled.collapsingnotifications.dagger.DaggerAppComponent

/**
 * Created by chetansachdeva on 12/10/17.
 */

class CollapsingNotificationsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        component().inject(this)
    }

    private val component: AppComponent by lazy {
        DaggerAppComponent.builder()
                .application(this)
                .build()
    }

    fun component() = component
}
