package com.fueled.collapsingnotifications.dagger

import android.content.Context

import com.fueled.collapsingnotifications.CollapsingNotificationsApplication

/**
 * Created by hussein@fueled.com on 03/05/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */

object Injector {

    fun getAppComponent(context: Context): AppComponent {
        return if (context is CollapsingNotificationsApplication) {
            context.component()
        } else {
            throw IllegalArgumentException("Provided context must be of type Application.")
        }
    }
}
