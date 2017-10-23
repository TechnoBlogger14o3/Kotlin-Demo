package com.kotlin.demo.supports

import android.content.Context
import android.content.SharedPreferences

/***
 * Created by Techno Blogger on 23/10/17.
 */
class AppPreferences(context: Context) {

    private val PREFS_FILENAME = "com.kotlin.demo"
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var userEmail: String
        get() = sharedPreferences.getString(AppConstants().strPrefUserName, "")
        set(value) = sharedPreferences.edit().putString(AppConstants().strPrefUserName, value).apply()
}