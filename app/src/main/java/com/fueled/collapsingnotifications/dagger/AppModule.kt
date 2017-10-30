package com.fueled.collapsingnotifications.dagger

import android.app.Application
import dagger.Module
import android.content.Context
import android.content.SharedPreferences
import dagger.Provides
import com.fueled.collapsingnotifications.data.PreferenceManagerImpl
import com.fueled.collapsingnotifications.core.PreferenceManager
import com.google.gson.Gson

/**
 * Created by chetansachdeva on 12/10/17.
 */

@Module
class AppModule {

    val PREFS_NAME = "CollapsingNotifications"

    @Provides
    @PerApplication
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @PerApplication
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @PerApplication
    fun providePreferenceManager(sharedPreferences: SharedPreferences, gson: Gson): PreferenceManager {
        return PreferenceManagerImpl(sharedPreferences, gson)
    }
}
