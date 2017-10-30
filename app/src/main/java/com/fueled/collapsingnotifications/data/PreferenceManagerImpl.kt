package com.fueled.collapsingnotifications.data

import android.content.SharedPreferences
import com.fueled.collapsingnotifications.util.MultiValuedMap
import com.fueled.collapsingnotifications.core.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Copyright (c) 2017 Fueled. All rights reserved.
 *
 * @author chetansachdeva on 03/07/17
 */

@Singleton
class PreferenceManagerImpl @Inject
constructor(private val sharedPreferences: SharedPreferences, private val gson: Gson) : PreferenceManager {

    override fun putMultiValuedMap(key: String, value: MultiValuedMap<String, Int>) {
        sharedPreferences.edit().putString(key, gson.toJson(value)).apply()
    }

    override fun getMultiValuedMap(key: String): MultiValuedMap<String, Int> {
        return gson.fromJson(sharedPreferences.getString(key,
                gson.toJson(MultiValuedMap<String, Int>())),
                object : TypeToken<MultiValuedMap<String, Int>>() {
                }.getType())
    }
}
