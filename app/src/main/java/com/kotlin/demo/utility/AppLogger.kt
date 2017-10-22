package com.kotlin.demo.utility

import com.kotlin.demo.BuildConfig
import java.io.PrintWriter
import java.io.StringWriter

/***
 * Created by Techno Blogger on 25/5/16.
 */
class AppLogger {

    fun e(tag: String, msg: String) {
        if (!BuildConfig.LOG_MODE)
            return
        if (msg.length > 4000) {
            android.util.Log.e(tag, msg.substring(0, 4000))
            e("", "" + checkInstanceOfException(msg.substring(4000)))
        } else
            android.util.Log.e(tag, "" + checkInstanceOfException(msg))
    }

    fun i(tag: String, msg: String) {
        if (!BuildConfig.LOG_MODE)
            return
        if (msg.length > 4000) {
            android.util.Log.i(tag, msg.substring(0, 4000))
            e("", "" + checkInstanceOfException(msg.substring(4000)))
        } else
            android.util.Log.i(tag, "" + checkInstanceOfException(msg))
    }

    fun d(tag: String, msg: String) {
        if (!BuildConfig.LOG_MODE)
            return
        if (msg.length > 4000) {
            android.util.Log.d(tag, msg.substring(0, 4000))
            e("", "" + checkInstanceOfException(msg.substring(4000)))
        } else
            android.util.Log.d(tag, "" + checkInstanceOfException(msg))
    }

    fun w(tag: String, msg: String) {
        if (!BuildConfig.LOG_MODE)
            return
        if (msg.length > 4000) {
            android.util.Log.w(tag, msg.substring(0, 4000))
            e("", "" + checkInstanceOfException(msg.substring(4000)))
        } else
            android.util.Log.w(tag, "" + checkInstanceOfException(msg))
    }

    private fun checkInstanceOfException(msg: Any): Any {
        var msg = msg
        try {
            if (msg is Exception) {
                val errors = StringWriter()
                msg.printStackTrace(PrintWriter(errors))
                msg = errors.toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return msg
    }
}
