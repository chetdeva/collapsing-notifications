package com.fueled.collapsingnotifications.data

import com.fueled.collapsingnotifications.core.PreferenceManager
import com.fueled.collapsingnotifications.util.MultiValuedMap

/**
 * Copyright (c) 2017 Fueled. All rights reserved.
 *
 * @author chetansachdeva on 25/09/17
 */

class CollapsingNotificationStore(private val preferenceManager: PreferenceManager) {

    var collapsingNotifications: MultiValuedMap<String, Int>
        get() = preferenceManager.getMultiValuedMap(PreferenceManager.COLLAPSING_NOTIFICATIONS)
        set(value) = preferenceManager.putMultiValuedMap(PreferenceManager.COLLAPSING_NOTIFICATIONS, value)

}
