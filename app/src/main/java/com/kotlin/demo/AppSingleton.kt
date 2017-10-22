package com.kotlin.demo

import android.app.Application

import com.kotlin.demo.activities.MainActivity
import com.kotlin.demo.utility.FragmentOrganiser


class AppSingleton : Application() {
    var activityInstance: MainActivity? = null
        private set
    private var fragmentOrganiser: FragmentOrganiser? = null

    val flowOrganization: FragmentOrganiser
        get() {
            if (fragmentOrganiser == null)
                fragmentOrganiser = FragmentOrganiser(this.activityInstance!!, R.id.frameParent)
            return fragmentOrganiser!!
        }

    fun init(_mainActivity: MainActivity) {
        this.activityInstance = _mainActivity
    }

    fun onResume() {
        if (fragmentOrganiser != null)
            fragmentOrganiser!!.onResume()
    }

    fun onPause() {
        if (fragmentOrganiser != null)
            fragmentOrganiser!!.onPause()
    }

    fun onDestroy() {
        appSingleton = null
        activityInstance = null
        fragmentOrganiser = null
    }

    companion object {
        private var appSingleton: AppSingleton? = null
        val instance: AppSingleton
            get() {
                if (appSingleton == null)
                    appSingleton = AppSingleton()
                return appSingleton!!
            }
    }

}