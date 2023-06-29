package com.example.facilityproject.utils

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.example.facilityproject.FacilityProject
import com.example.facilityproject.R

/**
 *
 *
 * Purpose of this class is to save data in preference and retrieve values from preference throughout the lifecycle of application
 * This class is hold methods for storing and retrieving values from preference.
 *
 */
class Preference{

    private val sharedPreferences: SharedPreferences = FacilityProject.instance.getSharedPreferences(
        FacilityProject.instance.getString(
            R.string.app_name
        ), Context.MODE_PRIVATE
    )


    /**
     * Returns the userId from the Shared Preference file
     *
     * @return userId
     */
    /**
     * Stores the userId into Shared Preference file
     */
    var timeStamp: Long
        get() = sharedPreferences.getLong(PREF_KEY_TIMESTAMP, 0L)
        set(userId) = sharedPreferences.edit().putLong(PREF_KEY_TIMESTAMP, userId).apply()


    companion object {
        private var preference: Preference? = null

        // key
        const val PREF_KEY_TIMESTAMP = "PREF_KEY_TIMESTAMP"

        /**
         * @return the [SharedPreferences] object that will be used to save values in the application preference
         */
        val instance: Preference
            get() {
                if (preference == null) {
                    preference = Preference()
                }
                return preference!!
            }
    }


}
