package com.kotlin.demo.utility

import java.util.regex.Pattern

class MyValidations {
    // Email validity check
    fun isValidEmail(email: String): Boolean {
        if (!isValidString(email))
            return false
        val EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        val pattern = Pattern.compile(EMAIL_PATTERN)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    // String validator
    private fun isValidString(data: String?): Boolean {
        return !(data == null || data.trim { it <= ' ' }.isEmpty() || data.equals("null", ignoreCase = true))
    }

}
