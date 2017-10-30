package com.fueled.collapsingnotifications.core

import com.fueled.collapsingnotifications.util.MultiValuedMap

/**
 * Copyright (c) 2017 Fueled. All rights reserved.
 *
 * @author chetansachdeva on 03/07/17
 */

interface PreferenceManager {

    fun putMultiValuedMap(key: String, value: MultiValuedMap<String, Int>)

    fun getMultiValuedMap(key: String) : MultiValuedMap<String, Int>

    companion object {
        val COLLAPSING_NOTIFICATIONS = "COLLAPSING_NOTIFICATIONS"
    }

}
