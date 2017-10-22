package com.kotlin.demo.utility

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build

class CheckNetworkState(private val context: Context?) {

    val isNetworkAvailable: Boolean
        get() {
            val isNetworkAvailable = false
            if (context != null) {
                val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val networks = connectivityManager.allNetworks
                    var networkInfo: NetworkInfo
                    for (mNetwork in networks) {
                        networkInfo = connectivityManager.getNetworkInfo(mNetwork)
                        if (networkInfo.state == NetworkInfo.State.CONNECTED) {
                            return true
                        }
                    }
                } else {
                    if (connectivityManager != null) {
                        val info = connectivityManager.allNetworkInfo
                        info?.filter { it.state == NetworkInfo.State.CONNECTED }?.forEach { return true }
                    }
                }
            }
            return isNetworkAvailable
        }
}
